package com.autG.oncln.dtos.requests

import java.time.LocalDate

data class EquipmentsRequest(
    val instalacao: String,
    val potencia: Int,
    val qtdEquipamento: Int,
    val tipo: String,
    val vidaUtil: Int
)