package com.autG.oncln.dtos.responses

data class RoomsItem(
    val floor: Int,
    val idRoom: Int,
    val name: String,
    private val onClick: (msg: String) -> Unit
)