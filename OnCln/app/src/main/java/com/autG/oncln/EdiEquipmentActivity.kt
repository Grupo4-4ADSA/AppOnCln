package com.autG.oncln

import android.content.SharedPreferences
import android.os.Bundle
import android.transition.Fade
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.autG.oncln.api.Rest
import com.autG.oncln.databinding.ActivityEditEquipmentBinding
import com.autG.oncln.dtos.requests.*
import com.autG.oncln.dtos.responses.Generic
import com.autG.oncln.services.Auth
import com.autG.oncln.services.NavigationHost
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class EditEquipmentActivity(val idEquipment: Int, var nameRoom: String) : Fragment() {

    private lateinit var binding: ActivityEditEquipmentBinding
    private val retrofit = Rest.getInstance()
    private lateinit var prefs: SharedPreferences
    private var action = 0
    private lateinit var dateFormatedDb : String
    private val DAYMILISSECONDS = 86400000
    var list = arrayOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityEditEquipmentBinding.inflate(inflater, container, false)

        binding.includeText.textTitulo.text= getText(R.string.title_input_edit_equipment)
        binding.btnEquipment.botaoAzul.text = getText(R.string.txt_btn_save)
        TransitionManager.beginDelayedTransition(container, Fade())

        val items = arrayOf(
            getText(R.string.txt_air).toString(),
            getText(R.string.txt_set_of_lamps).toString(),
            getText(R.string.txt_socket_set).toString()
        )
        (binding.textFieldEquip.editText as? MaterialAutoCompleteTextView)?.setSimpleItems(items)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prefs =
            requireActivity().getSharedPreferences("preferences", AppCompatActivity.MODE_PRIVATE)

        with(binding) {

            textNameRoom.setText("Sala: $nameRoom")

            btnEquipment.botaoAzul.setOnClickListener {
                editarEquipamento()
            }
        }

        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText(getText(R.string.hint_data))
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()

        binding.datePickerActions.setOnClickListener {
            if (action == 0) {
                datePicker.show(activity?.supportFragmentManager!!, "date_picker")
                action = 1
            }
        }

        datePicker.addOnPositiveButtonClickListener {
            val date = datePicker.selection?.let { it1 -> Date(it1) }
            val dateFormated = SimpleDateFormat("dd/MM/yyyy")
            dateFormatedDb = SimpleDateFormat("yyyy-MM-dd").format(date?.time?.plus(DAYMILISSECONDS)
                ?: DAYMILISSECONDS)
            binding.datePickerActions.setText(
                dateFormated.format(
                    date?.time?.plus(DAYMILISSECONDS) ?: DAYMILISSECONDS
                )
            )
            action = 0
        }

        binding.btnEquipment.botaoAzul.setOnClickListener {
            editarEquipamento()
        }

    }

    private fun editarEquipamento() {
        val data = list.indexOf((binding.textNameRoom as? TextInputLayout)?.toString())

        val typeEquipment = (binding.selectEquipment as? MaterialAutoCompleteTextView)?.text.toString()
        val qtdEquipment = binding.inputQtd.editText?.text.toString().toInt()
        val powerEquipment = binding.inputPower.editText?.text.toString().toInt()
        val life = binding.inputLife.editText?.text.toString().toInt()


        val body = EquipmentsRequest(
            instalacao = dateFormatedDb,
            potencia = powerEquipment,
            qtdEquipamento = qtdEquipment,
            tipo = typeEquipment,
            vidaUtil = life,)


        val registerRequest = retrofit
            .create(Auth::class.java)

        registerRequest.editEquipments(body, idEquipment).enqueue(
            object : Callback<Generic> {
                override fun onResponse(
                    call: Call<Generic>,
                    response: Response<Generic>
                ) {
                    when {
                        response.isSuccessful -> {

                            //binding.textNameRoom.text = nameRoom

                            Toast.makeText(
                                context,
                                //TODO Colocar depois o item resposta
                                "Sala editada com sucesso!",
                                Toast.LENGTH_LONG
                            ).show()

                            (activity as NavigationHost).navigateTo(
                                EquipmentsActivity(), addToBackStack = true,
                                R.layout.activity_equipments
                            )
                        }

                        else -> {
                            Toast.makeText(
                                context,
                                "Não foi possivel editar",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }

                override fun onFailure(call: Call<Generic>, t: Throwable) {
                    Toast.makeText(context, getText(R.string.txt_offline_system), Toast.LENGTH_LONG)
                        .show()
                }
            })
    }
}

