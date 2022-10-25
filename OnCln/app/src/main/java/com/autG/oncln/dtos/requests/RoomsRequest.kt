package com.autG.oncln.dtos.requests

data class RoomsRequest(
    val name : String,
    val floor : Int,
    val predio: Int = 251,
)