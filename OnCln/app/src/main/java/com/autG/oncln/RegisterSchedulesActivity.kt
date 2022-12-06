package com.autG.oncln

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.text.format.DateFormat.is24HourFormat
import android.transition.Fade
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.autG.oncln.api.Rest
import com.autG.oncln.databinding.ActivityRegisterScheduleBinding
import com.autG.oncln.dtos.requests.ClnBox
import com.autG.oncln.dtos.requests.Sala
import com.autG.oncln.dtos.requests.Schedule
import com.autG.oncln.dtos.responses.Rooms
import com.autG.oncln.services.Auth
import com.autG.oncln.services.NavigationHost
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

internal class RegisterSchedulesActivity : Fragment() {

    private lateinit var binding: ActivityRegisterScheduleBinding
    private lateinit var dateFormatedDb: String
    private lateinit var prefs: SharedPreferences
    private val retrofit = Rest.getInstance()

    private val DAYMILISSECONDS = 86400000

    private var action = 0

    var list = arrayOf<String>()
    var listId = arrayOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        TransitionManager.beginDelayedTransition(container, Fade())

        binding = ActivityRegisterScheduleBinding.inflate(inflater, container, false)
        binding.includeText.textTitulo.text = getText(R.string.title_input_new_scheduling)
        binding.btnAgendamento.botaoAzul.text = getText(R.string.txt_btn_save)

        binding.radioButton1.isChecked = true

        prefs = requireContext().getSharedPreferences("preferences", AppCompatActivity.MODE_PRIVATE)

        val cacheLogin = prefs.getInt("idPredio", 0)

        requestRooms(cacheLogin)

        return binding.root
    }

    @SuppressLint("Range")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var checkedRadio = true
        var time = ""

        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            checkedRadio = when (checkedId) {
                R.id.radio_button_1 -> true
                else -> false
            }
        }

        val picker =
            MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .setHour(12)
                .setMinute(10)
                .setTitleText("Selecione um horario")
                .build()

        picker.addOnPositiveButtonClickListener {
            time = "${picker.hour}:${picker.minute}:00"
            binding.inputLife.setText(time)
        }

        val constraintsBuilder =
            CalendarConstraints.Builder()
                .setValidator(DateValidatorPointForward.now())

        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setCalendarConstraints(constraintsBuilder.build())
                .setTitleText(getText(R.string.hint_data))
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()

        binding.inputLife.setOnClickListener {
            picker.show(activity?.supportFragmentManager!!, "time_picker");
        }

        binding.datePickerActions.setOnClickListener {
            if (action == 0) {
                datePicker.show(activity?.supportFragmentManager!!, "date_picker")
                action = 1
            }
        }

        datePicker.addOnPositiveButtonClickListener {
            val date = datePicker.selection?.let { it1 -> Date(it1) }
            val dateFormated = SimpleDateFormat("dd/MM/yyyy")
            dateFormatedDb = SimpleDateFormat("yyyy-MM-dd").format(
                date?.time?.plus(DAYMILISSECONDS)
                    ?: DAYMILISSECONDS
            )
            binding.datePickerActions.setText(
                dateFormated.format(
                    date?.time?.plus(DAYMILISSECONDS) ?: DAYMILISSECONDS
                )
            )
            action = 0
        }

        binding.btnAgendamento.botaoAzul.setOnClickListener {
            cadastroAgendamento(checkedRadio, time)
        }

    }

    private fun requestRooms(cacheLogin: Int) {

        val authRequest = retrofit
            .create(Auth::class.java)

        authRequest.requestRoomsCln(cacheLogin).enqueue(
            object : Callback<Rooms?> {
                override fun onResponse(
                    call: Call<Rooms?>,
                    response: Response<Rooms?>
                ) {
                    if (response.isSuccessful) {

                        response.body()?.forEach {
                            list += "andar: ${it.floor}, ${it.name}"
                            listId += "${it.idRoom}"
                        }

                        //TODO falta retorno do backend
                        val adapter =
                            ArrayAdapter(requireContext(), R.layout.list_room_item, list)
                        (binding.textInputRoom.editText as? AutoCompleteTextView)?.setAdapter(
                            adapter
                        )

                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Falha ao carregar salas",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<Rooms?>, t: Throwable) {
                    Toast.makeText(context, getText(R.string.txt_offline_system), Toast.LENGTH_LONG)
                        .show()
                }
            })
    }


    private fun cadastroAgendamento(action: Boolean, time: String) {

        val data =
            list.indexOf((binding.textInputRoom.editText as? AutoCompleteTextView)?.text.toString())

        val sala = Sala(idRoom = listId[data].toInt())
        val body = Schedule(
            data = dateFormatedDb,
            horario = time,
            ligar = action,
            sala = sala,
        )


        val registerRequest = retrofit
            .create(Auth::class.java)

        registerRequest.schedule(body).enqueue(
            object : Callback<Schedule> {
                override fun onResponse(
                    call: Call<Schedule>,
                    response: Response<Schedule>
                ) {
                    when {
                        response.isSuccessful -> {

                            Toast.makeText(
                                context,
                                //TODO Colocar depois o item resposta
                                "Equipamento cadastrado com sucesso!",
                                Toast.LENGTH_LONG
                            ).show()

                            (activity as NavigationHost).navigateTo(
                                SchedulesActivity(), addToBackStack = true,
                                R.layout.activity_schedules
                            )
                        }

                        else -> {
                            Toast.makeText(
                                context,
                                "Não foi possivel cadastrar",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }

                override fun onFailure(call: Call<Schedule>, t: Throwable) {
                    Toast.makeText(context, getText(R.string.txt_offline_system), Toast.LENGTH_LONG)
                        .show()
                }
            })
    }


}