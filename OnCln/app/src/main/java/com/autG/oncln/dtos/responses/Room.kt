package com.autG.oncln.dtos.responses

data class Room(
    val floor: Int,
    val idRoom: Int,
    val name: String,
    val predio: Predio
)