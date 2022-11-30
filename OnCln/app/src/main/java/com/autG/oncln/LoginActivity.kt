package com.autG.oncln

import LoginResponse
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.autG.oncln.services.Auth
import com.autG.oncln.api.Rest
import com.autG.oncln.databinding.ActivityLoginBinding

import com.autG.oncln.dtos.requests.LoginRequest
import com.autG.oncln.services.NavigationHost
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

internal class LoginActivity : Fragment() {

    private val retrofit = Rest.getInstance()
    private lateinit var binding: ActivityLoginBinding
    private lateinit var prefs: SharedPreferences

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
                    addToBackStack = false,
                    R.layout.activity_edit_equipment
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
                                HomeActivity(),
                                addToBackStack = false,
                                R.layout.activity_home
                            )

                        }

                        else -> {
                            Toast.makeText(
                                context,
                                getText(R.string.txt_invalid_login),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(context, getText(R.string.txt_offline_system), Toast.LENGTH_LONG)
                        .show()

                }
            })
    }
}