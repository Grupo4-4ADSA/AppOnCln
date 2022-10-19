package com.autG.oncln.services

import SalaResponse
import com.autG.oncln.dtos.requests.SalaRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterRoom {

    @POST("/rooms")
    fun register(@Body body: SalaRequest):
            Call<SalaResponse>
}