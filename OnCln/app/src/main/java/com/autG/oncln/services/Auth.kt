package com.autG.oncln.services

import LoginResponse
import SalaResponse
import com.autG.oncln.dtos.requests.LoginRequest
import com.autG.oncln.dtos.requests.SalaRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface Auth {

    @POST("/usuarios/autenticacao")
    fun login(@Body body: LoginRequest):
            Call<LoginResponse>

    @POST("/rooms")
    fun register(@Body body: SalaRequest):
            Call<Any?>

}