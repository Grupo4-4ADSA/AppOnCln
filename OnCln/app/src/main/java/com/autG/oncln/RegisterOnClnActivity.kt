package com.autG.oncln

import OnClnResponse
import android.os.Bundle
import android.transition.Fade
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.autG.oncln.api.Rest
import com.autG.oncln.databinding.ActivityRegisterOnClnBinding
import com.autG.oncln.dtos.requests.OnClnRequest
import com.autG.oncln.dtos.requests.SalaRequest
import com.autG.oncln.dtos.responses.Rooms
import com.autG.oncln.models.Predio
import com.autG.oncln.services.Auth
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterOnClnActivity : Fragment() {
    private val retrofit = Rest.getInstance()
    private lateinit var binding: ActivityRegisterOnClnBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ActivityRegisterOnClnBinding.inflate(inflater, container, false)
        binding.includeText.textTitulo.text = getText(R.string.title_register_oncln)
        binding.btnQr.botaoAzul.text = getText(R.string.txt_register)
        TransitionManager.beginDelayedTransition(container, Fade())
        requestRooms()


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnQr.botaoAzul.setOnClickListener {
            cadastroOnCln()
        }

    }

    private fun requestRooms() {

        val authRequest = retrofit
            .create(Auth::class.java)

        authRequest.requestRooms().enqueue(
            object : Callback<Rooms?> {
                override fun onResponse(
                    call: Call<Rooms?>,
                    response: Response<Rooms?>
                ) {
                    if (response.isSuccessful) {

                        val salas: ArrayList<String> = arrayListOf()

                        response.body()?.forEach {
                            salas.add(it.name)
                        }

                        (binding.selectRoom as? MaterialAutoCompleteTextView)?.setSimpleItems(salas.toTypedArray())

                    } else {

                        Toast.makeText(
                            requireContext(),
                            "Falha ao carregar salas",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<Rooms?>, t: Throwable) {
                    Toast.makeText(context, "Sistema fora do ar", Toast.LENGTH_LONG).show()
                }
            })
    }

    private fun cadastroOnCln() {
        val roomName = binding.selectRoom.text.toString()
        val qrCodeValue = binding.inputQr.text.toString()
        //Para fazer a requisição de um novo oncln, o backend exige informações completas,
        //ao inves de somente o id da sala, por isso essa forma de linkar temporaria
        val body = OnClnRequest(
            sala = SalaRequest(roomName, floor = 1, predio = Predio()),
            qrCode = qrCodeValue,
            ip = "12345"
        )

        val registerRequest = retrofit
            .create(Auth::class.java)

        registerRequest.registerCLNBox(body).enqueue(
            object : Callback<OnClnResponse> {
                override fun onResponse(
                    call: Call<OnClnResponse>,
                    response: Response<OnClnResponse>
                ) {
                    when {
                        response.isSuccessful -> {
                            Toast.makeText(
                                context,
                                //TODO Colocar depois o item resposta
                                "OnCln cadastrado com sucesso!",
                                Toast.LENGTH_LONG
                            ).show()
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

                override fun onFailure(call: Call<OnClnResponse>, t: Throwable) {
                    Toast.makeText(context, "Sem conexão com servidor", Toast.LENGTH_LONG).show()
                }
            })


    }

}
