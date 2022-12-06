package com.autG.oncln

import android.content.SharedPreferences
import android.os.Bundle
import android.transition.Fade
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.autG.oncln.api.Rest
import com.autG.oncln.databinding.ActivityRegisterOnClnBinding
import com.autG.oncln.dtos.requests.ClnBox
import com.autG.oncln.dtos.requests.OnClnRequest
import com.autG.oncln.dtos.requests.Sala
import com.autG.oncln.dtos.responses.Rooms
import com.autG.oncln.services.Auth
import com.autG.oncln.services.NavigationHost
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterOnClnActivity : Fragment(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: ActivityRegisterOnClnBinding
    private val retrofit = Rest.getInstance()
    private lateinit var prefs: SharedPreferences

    var list = arrayOf<String>()
    var listId = arrayOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityRegisterOnClnBinding.inflate(inflater, container, false)
        binding.includeText.textTitulo.text = getText(R.string.title_register_oncln)
        binding.btnQr.botaoAzul.text = getText(R.string.txt_register)
        TransitionManager.beginDelayedTransition(container, Fade())

        prefs = requireContext().getSharedPreferences("preferences", AppCompatActivity.MODE_PRIVATE)

        val cacheLogin = prefs.getInt("idPredio", 0)

        requestRooms(cacheLogin)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnQr.botaoAzul.setOnClickListener {
            cadastrarCln()
        }

    }
    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        Toast.makeText(context, "Selecione um item", Toast.LENGTH_LONG)
            .show()
    }

    private fun requestRooms(cacheLogin: Int) {

        val authRequest = retrofit
            .create(Auth::class.java)

        authRequest.requestRoomsWithoutCln(cacheLogin).enqueue(
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

    fun cadastrarCln() {

        val data =
            list.indexOf((binding.textInputRoom.editText as? AutoCompleteTextView)?.text.toString())

        val idSala = Sala(listId[data].toInt())
        val qrCode = binding.inputQr.text.toString()


        val body = OnClnRequest(
            qrCode = qrCode,
            ip = "192.168.15.3",
            sala = idSala,
        )


        val registerRequest = retrofit
            .create(Auth::class.java)

        registerRequest.registerOncln(body).enqueue(
            object : Callback<OnClnRequest> {
                override fun onResponse(
                    call: Call<OnClnRequest>,
                    response: Response<OnClnRequest>
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

                override fun onFailure(call: Call<OnClnRequest>, t: Throwable) {
                    Toast.makeText(context, getText(R.string.txt_offline_system), Toast.LENGTH_LONG)
                        .show()
                }
            })
    }


}

