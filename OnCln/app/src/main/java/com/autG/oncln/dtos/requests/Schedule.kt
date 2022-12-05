package com.autG.oncln.dtos.requests

data class Schedule(
    val data: String,
    val horario: String,
    val ligar: Boolean,
    val sala: Sala
)