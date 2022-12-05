package com.autG.oncln.dtos.responses

import com.autG.oncln.dtos.requests.ClnBox

data class RoomsItem(
    val floor: Int,
    val idRoom: Int,
    val name: String,
    val idClnBox: Int?,
    private val onClick: (msg: String) -> Unit
)