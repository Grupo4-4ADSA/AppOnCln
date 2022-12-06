package com.autG.oncln.services

import LoginResponse
import SalaResponse
import com.autG.oncln.dtos.requests.*
import com.autG.oncln.dtos.responses.*
import com.autG.oncln.dtos.responses.EquipmentsResponse
import retrofit2.Call
import retrofit2.http.*

interface Auth {

    @POST("/usuarios/autenticacao")
    fun login(@Body body: LoginRequest):
            Call<LoginResponse>

    @POST("/rooms")
    fun register(@Body body: SalaRequest):
            Call<SalaResponse>

    @GET("/rooms/all/{idBuilding}")
    fun requestRooms(@Path("idBuilding") id: Int):
            Call<Rooms>

    @GET("/rooms/apenas/salas/{idBuilding}")
    fun requestRoomsWithoutCln(@Path("idBuilding") id: Int):
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

    @PUT("/rooms/{idRoom}")
    fun editRooms(@Body body: RoomsRequest, @Path("idRoom") id: Int):
            Call<Generic>

    @DELETE("/rooms/{idRoom}")
    fun deleteRooms(@Path("idRoom") id: Int):
            Call<Generic>

    @GET("/equipments/predio/{idBuilding}")
    fun requestEquipments(@Path("idBuilding") id: Int):
            Call<EquipmentResponse>

    @GET("/equipments/{idRoom}")
    fun requestEquipmentsRoom(@Path("idRoom") id: Int):
            Call<EquipmentResponse>


}