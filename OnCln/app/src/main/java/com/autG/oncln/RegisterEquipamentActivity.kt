package com.autG.oncln

import android.os.Bundle
import android.transition.Fade
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.autG.oncln.api.Rest
import com.autG.oncln.databinding.ActivityRegisterEquipamentBinding
import com.autG.oncln.dtos.responses.Rooms
import com.autG.oncln.services.Auth
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterEquipamentActivity : Fragment() {
    private val retrofit = Rest.getInstance()
    private lateinit var binding: ActivityRegisterEquipamentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = ActivityRegisterEquipamentBinding.inflate(inflater, container, false)
        binding.includeText.textTitulo.text = getText(R.string.title_register_equipment)
        binding.btnEquipment.botaoAzul.text = getText(R.string.txt_register)

        TransitionManager.beginDelayedTransition(container, Fade())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requestRooms()
        val items = arrayOf(
            getText(R.string.txt_air).toString(),
            getText(R.string.txt_set_of_lamps).toString(),
            getText(R.string.txt_socket_set).toString()
        )
        (binding.textFieldEquip.editText as? MaterialAutoCompleteTextView)?.setSimpleItems(items)
    }

    private fun requestRooms() {

        val authRequest = retrofit.create(Auth::class.java)

        authRequest.requestRooms().enqueue(object : Callback<Rooms?> {
            override fun onResponse(
                call: Call<Rooms?>, response: Response<Rooms?>
            ) {
                if (response.isSuccessful) {

                    val salas: ArrayList<String> = arrayListOf()

                    response.body()?.forEach {
                        salas.add(it.name)
                    }

                    (binding.selectRoom as? MaterialAutoCompleteTextView)?.setSimpleItems(salas.toTypedArray())

                } else {

                    Toast.makeText(
                        requireContext(), "Falha ao carregar salas", Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<Rooms?>, t: Throwable) {
                Toast.makeText(context, "Sistema fora do ar", Toast.LENGTH_LONG).show()
            }
        })
    }

}