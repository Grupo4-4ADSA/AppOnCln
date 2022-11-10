package com.autG.oncln

import LoginResponse
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.autG.oncln.services.Auth
import com.autG.oncln.api.Rest
import com.autG.oncln.databinding.ActivityLoginBinding

import com.autG.oncln.dtos.requests.LoginRequest
import com.autG.oncln.services.NavigationHost
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

internal class Login : Fragment() {
    private val retrofit = Rest.getInstance()
    private lateinit var binding: ActivityLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            btnEntrar.setOnClickListener {
                (activity as NavigationHost).navigateTo(
                    HomeActivity(),
                    addToBackStack = false
                )
            }
        }

    }

    private fun trylogin() {
        val login = binding.inputUsuario.text.toString()
        val senha = binding.inputSenha.text.toString()
        val body = LoginRequest(login, senha)

        val authRequest = retrofit
            .create(Auth::class.java)

        authRequest.login(body).enqueue(
            object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    when {
                        response.isSuccessful -> {
                            (activity as NavigationHost).navigateTo(
                                CadastroOnClnActivity(),
                                addToBackStack = false
                            )
                        }

                        else -> {
                            Toast.makeText(
                                context,
                                "Login invalido",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(context, "Sistema fora do ar", Toast.LENGTH_LONG).show()

                }
            })
    }
}