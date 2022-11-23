package com.autG.oncln.services

import LoginResponse
import OnClnResponse
import SalaResponse
import com.autG.oncln.dtos.requests.LoginRequest
import com.autG.oncln.dtos.requests.OnClnRequest
import com.autG.oncln.dtos.requests.SalaRequest
import com.autG.oncln.dtos.responses.Rooms
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Auth {

    @POST("/usuarios/autenticacao")
    fun login(@Body body: LoginRequest):
            Call<LoginResponse>

    @POST("/rooms")
    fun register(@Body body: SalaRequest):
            Call<SalaResponse>

    //TODO mudar url no futuro colocar dinamica {idPredio}
    @GET("/rooms/all/251")
    fun requestRooms():
            Call<Rooms>

    @POST("/clnboxex")
    fun registerCLNBox(@Body body: OnClnRequest):
            Call<OnClnResponse>

}
