package com.autG.oncln

import SalaResponse
import android.os.Bundle
import android.transition.Fade
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.autG.oncln.api.Rest
import com.autG.oncln.databinding.ActivityRegisterRoomBinding
import com.autG.oncln.dtos.requests.Predio
import com.autG.oncln.dtos.requests.SalaRequest
import com.autG.oncln.services.Auth
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

internal class RegisterRoomActivity : Fragment() {
    private val retrofit = Rest.getInstance()
    private lateinit var binding: ActivityRegisterRoomBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityRegisterRoomBinding.inflate(inflater, container, false)


        binding.includeText.textTitulo.text = getText(R.string.title_rooms)

        binding.btnQr.botaoAzul.text = getText(R.string.txt_register)

        TransitionManager.beginDelayedTransition(container, Fade())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            binding.btnQr.botaoAzul.setOnClickListener {
                cadastroSala()
            }
        }
    }

    private fun cadastroSala() {
        val nomeSala = binding.inputNameRoom.text.toString()
        //Todo Implementar a versão spinner futuramente
        val nomeAndar = binding.selectFloor.text.toString().toInt()
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
                            Toast.makeText(
                                context,
                                //TODO Colocar depois o item resposta
                                "Sala cadastrada com sucesso!",
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

                override fun onFailure(call: Call<SalaResponse>, t: Throwable) {
                    Toast.makeText(context, "Sem conexão com servidor", Toast.LENGTH_LONG).show()
                }
            })
    }


}

