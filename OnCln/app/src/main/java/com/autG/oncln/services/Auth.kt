package com.autG.oncln.services

import LoginResponse
import SalaResponse
import com.autG.oncln.dtos.requests.*
import com.autG.oncln.dtos.responses.Buildings
import com.autG.oncln.dtos.responses.OnClnResponse
import com.autG.oncln.dtos.responses.OnclnItem
import com.autG.oncln.dtos.responses.Rooms
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Auth {

    @POST("/usuarios/autenticacao")
    fun login(@Body body: LoginRequest):
            Call<LoginResponse>

    @POST("/rooms")
    fun register(@Body body: SalaRequest):
            Call<SalaResponse>

    //TODO mudar url no futuro colocar dinamica {idPredio}
    @GET("/rooms/all/{idBuilding}")
    fun requestRooms(@Path("idBuilding") id: Int):
            Call<Rooms>

    @GET("/rooms/{idBuilding}")
    fun requestRoomsCln(@Path("idBuilding") id: Int):
            Call<Rooms>

    @GET("/predio/{idBuilding}")
    fun requestBuildings(@Path("idBuilding") id: String):
            Call<Buildings>


    @POST("/clnboxex")
    fun registerOncln(@Body body: OnClnRequest):
            Call<OnClnRequest>

    @GET("/clnboxex")
    fun requestOnclns():
            Call<OnClnResponse>

    @POST("/equipments")
    fun equipments(@Body body: Equipments):
            Call<Equipments>

    @POST("/agendamentos")
    fun schedule(@Body body: Schedule):
            Call<Schedule>
}