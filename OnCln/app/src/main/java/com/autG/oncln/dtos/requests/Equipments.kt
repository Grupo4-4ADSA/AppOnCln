package com.autG.oncln.dtos.requests

import java.time.LocalDate

data class Equipments(
    val clnBox: ClnBox,
    val instalacao: String,
    val nome: String,
    val porta: Int,
    val potencia: Int,
    val qtdEquipamento: Int,
    val tipo: String,
    val vidaUtil: Int
)