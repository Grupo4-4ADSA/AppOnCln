package com.autG.oncln.dtos.responses

import com.autG.oncln.dtos.requests.ClnBox

data class RoomsItem(
    val floor: Int,
    val idRoom: Int,
    val name: String,
    val idClnBox: Int?,
    private val onClick: (id: Int, floorItem: Int) -> Unit,
    private val delete: (idRoom: Int, nameRoom: String) -> Unit,
    private val onClickAcess: (idRoom: Int,nameRoom: String,floor: Int) -> Unit
)