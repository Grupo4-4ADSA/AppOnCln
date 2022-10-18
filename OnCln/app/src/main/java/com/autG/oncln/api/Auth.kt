package com.autG.oncln.api

import com.autG.oncln.dtos.requests.LoginRequest
import com.autG.oncln.dtos.responses.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface Auth {

    @POST("/usuarios/autenticacao")
    fun login(@Body body: LoginRequest):
            Call<LoginResponse>
}