package com.autG.oncln

import android.content.SharedPreferences
import android.os.Bundle
import android.transition.Fade
import android.transition.TransitionManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.autG.oncln.api.Rest
import com.autG.oncln.databinding.ActivityRegisterEquipamentBinding
import com.autG.oncln.dtos.requests.ClnBox
import com.autG.oncln.dtos.requests.Equipments
import com.autG.oncln.dtos.responses.Rooms
import com.autG.oncln.services.Auth
import com.autG.oncln.services.NavigationHost
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class RegisterEquipamentActivity : Fragment(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: ActivityRegisterEquipamentBinding
    private val retrofit = Rest.getInstance()
    private lateinit var prefs: SharedPreferences
    private var itemSelected = ""
    private lateinit var dateFormatedDb : String

    private val DAYMILISSECONDS = 86400000

    private var action = 0

    var list = arrayOf<String>()
    var listId = arrayOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = ActivityRegisterEquipamentBinding.inflate(inflater, container, false)
        binding.includeText.textTitulo.text = getText(R.string.title_register_equipment)
        binding.btnEquipment.botaoAzul.text = getText(R.string.txt_register)

        TransitionManager.beginDelayedTransition(container, Fade())

        prefs = requireContext().getSharedPreferences("preferences", AppCompatActivity.MODE_PRIVATE)

        val cacheLogin = prefs.getInt("idPredio", 0)

        requestRooms(cacheLogin)
        val items = arrayOf(
            getText(R.string.txt_air).toString(),
            getText(R.string.txt_set_of_lamps).toString(),
            getText(R.string.txt_socket_set).toString()
        )
        (binding.textFieldEquip.editText as? MaterialAutoCompleteTextView)?.setSimpleItems(items)
//dialog
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
            cadastroEquipamento()
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
                            listId += "${it.idClnBox}"
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

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        itemSelected = listId[p2]
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        Toast.makeText(context, "Selecione um item", Toast.LENGTH_LONG)
            .show()
    }

    private fun cadastroEquipamento() {

        val data = list.indexOf((binding.textInputRoom.editText as? AutoCompleteTextView)?.text.toString())

        val clnboxId = ClnBox(listId[data].toInt())
        val typeEquipment = (binding.textFieldEquip.editText as? MaterialAutoCompleteTextView)?.text.toString()
        val qtdEquipment = binding.inputQtd.editText?.text.toString().toInt()
        val powerEquipment = binding.inputPower.editText?.text.toString().toInt()
        val life = binding.inputLife.editText?.text.toString().toInt()

        val body = Equipments(
            clnBox = clnboxId,
                    instalacao = dateFormatedDb,
                    nome = typeEquipment,
                    porta = 2,
                    potencia = powerEquipment,
                    qtdEquipamento = qtdEquipment,
                    tipo = typeEquipment,
                    vidaUtil = life,
        )


        val registerRequest = retrofit
            .create(Auth::class.java)

        registerRequest.equipments(body).enqueue(
            object : Callback<Equipments> {
                override fun onResponse(
                    call: Call<Equipments>,
                    response: Response<Equipments>
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
                                RoomsActivity(), addToBackStack = true,
                                R.layout.activity_rooms
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

                override fun onFailure(call: Call<Equipments>, t: Throwable) {
                    Toast.makeText(context, getText(R.string.txt_offline_system), Toast.LENGTH_LONG)
                        .show()
                }
            })
    }
}