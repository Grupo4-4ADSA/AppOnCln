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
import com.autG.oncln.databinding.ActivityEditRoomBinding
import com.autG.oncln.databinding.ActivityRegisterRoomBinding
import com.autG.oncln.dtos.requests.Predio
import com.autG.oncln.dtos.requests.RoomsRequest
import com.autG.oncln.dtos.requests.SalaRequest
import com.autG.oncln.dtos.responses.Generic
import com.autG.oncln.services.Auth
import com.autG.oncln.services.NavigationHost
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.floor

class EditRoomActivity(val idRoom: Int,val floorItem: Int) : Fragment() {

    private lateinit var binding: ActivityEditRoomBinding
    private val retrofit = Rest.getInstance()
    private lateinit var prefs: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityEditRoomBinding.inflate(inflater, container, false)

        binding.includeText.textTitulo.text= getText(R.string.title_edit_room)
        binding.btnEditRoom.botaoAzul.text = getText(R.string.txt_btn_save)
        TransitionManager.beginDelayedTransition(container, Fade())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prefs =
            requireActivity().getSharedPreferences("preferences", AppCompatActivity.MODE_PRIVATE)

        with(binding) {

            textInputRoom.setText("Andar: $floorItem")

            btnEditRoom.botaoAzul.setOnClickListener {
                cadastroSala()
            }
        }
    }

    private fun cadastroSala() {
        val nomeSala = binding.inputNameRoom.text.toString()

        val body = RoomsRequest(nomeSala, floorItem)


        val registerRequest = retrofit
            .create(Auth::class.java)

        registerRequest.editRooms(body, idRoom).enqueue(
            object : Callback<Generic> {
                override fun onResponse(
                    call: Call<Generic>,
                    response: Response<Generic>
                ) {
                    when {
                        response.isSuccessful -> {

                            binding.inputNameRoom.text = null

                            Toast.makeText(
                                context,
                                //TODO Colocar depois o item resposta
                                "Sala editada com sucesso!",
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

