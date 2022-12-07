package com.autG.oncln.dtos.responses

import com.autG.oncln.dtos.requests.ClnBox
import com.autG.oncln.dtos.requests.RoomsRequest
import com.autG.oncln.dtos.requests.Sala

data class SchedulesItem(
    val id:Int,
    val data: String,
    val horario: String,
    val ligar: Boolean,
    val sala: RoomsRequest,
    private val onClick: (id: Int, nameRoom: String) -> Unit,
    private val delete: (idRoom: Int, nameRoom: String) -> Unit
)