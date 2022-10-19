package com.autG.oncln.services

import LoginResponse
import com.autG.oncln.dtos.requests.LoginRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface Auth {

    @POST("/usuarios/autenticacao")
    fun login(@Body body: LoginRequest):
            Call<LoginResponse>
}