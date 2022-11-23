package com.autG.oncln.dtos.requests

import com.autG.oncln.models.Predio

data class SalaRequest(
    val name: String,
    val floor: Int,
    val predio: Predio
)
