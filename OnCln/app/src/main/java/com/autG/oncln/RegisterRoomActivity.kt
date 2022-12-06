package com.autG.oncln

import SalaResponse
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
import com.autG.oncln.databinding.ActivityRegisterRoomBinding
import com.autG.oncln.dtos.requests.Predio
import com.autG.oncln.dtos.requests.SalaRequest
import com.autG.oncln.services.Auth
import com.autG.oncln.services.NavigationHost
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

internal class RegisterRoomActivity : Fragment(), AdapterView.OnItemSelectedListener{

    private val retrofit = Rest.getInstance()
    private lateinit var binding: ActivityRegisterRoomBinding
    private lateinit var prefs: SharedPreferences
    private var itemSelected = "0"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityRegisterRoomBinding.inflate(inflater, container, false)

        with(binding) {
            includeText.textTitulo.text = getText(R.string.title_rooms)

            btnQr.botaoAzul.text = getText(R.string.txt_register)
        }
        TransitionManager.beginDelayedTransition(container, Fade())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prefs =
            requireActivity().getSharedPreferences("preferences", AppCompatActivity.MODE_PRIVATE)

        val floor = prefs.getInt("andares", 0)
        val subsolo = prefs.getInt("subsolos", 0) - 1

        var list = arrayOf<String>()
        if (subsolo > 0) {
            for (j in 0..subsolo) {
                list += "${j + 1}º Subsolo"
            }
        }
        for (i in 0..floor) {
            if (i == 0) {
                list += "Terreo"
            } else {
                list += "${i}º Andar"
            }
        }

        with(binding) {

            val adapter = ArrayAdapter(requireContext(), R.layout.list_room_item, list)
            (textInputRoom.editText as? AutoCompleteTextView)?.setAdapter(adapter)

            btnQr.botaoAzul.setOnClickListener {
                (textInputRoom.editText as? MaterialAutoCompleteTextView)?.onItemSelectedListener = this@RegisterRoomActivity
                itemSelected =
                    (textInputRoom.editText as? MaterialAutoCompleteTextView)?.text.toString() ?: ""

                if (itemSelected.equals("")){
                    Toast.makeText(
                        context,
                        "Por favor insira o andar",
                        Toast.LENGTH_LONG
                    ).show()
                }else {
                    cadastroSala()
                }
            }
        }
    }

    private fun cadastroSala() {
        val nomeSala = binding.inputNameRoom.text.toString()
        val nomeAndar = when {
            itemSelected.indexOf("Subsolo") >= 0 ->
                itemSelected.replace("º Subsolo", "").toInt()
            itemSelected.indexOf("Terreo") >= 0 -> 0
            else -> itemSelected.replace("º Andar", "").toInt()
        }
        val body = SalaRequest(nomeSala, nomeAndar, Predio(251))


        val registerRequest = retrofit
            .create(Auth::class.java)

        registerRequest.register(body).enqueue(
            object : Callback<SalaResponse> {
                override fun onResponse(
                    call: Call<SalaResponse>,
                    response: Response<SalaResponse>
                ) {
                    when {
                        response.isSuccessful -> {
                            (binding.textInputRoom.editText as? MaterialAutoCompleteTextView)?.text = null
                            binding.inputNameRoom.text = null

                            Toast.makeText(
                                context,
                                //TODO Colocar depois o item resposta
                                "Sala cadastrada com sucesso!",
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

                override fun onFailure(call: Call<SalaResponse>, t: Throwable) {
                    Toast.makeText(context, getText(R.string.txt_offline_system), Toast.LENGTH_LONG)
                        .show()
                }
            })
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        itemSelected = p2.toString()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        Toast.makeText(context, "Selecione um item", Toast.LENGTH_LONG)
            .show()
    }
}

