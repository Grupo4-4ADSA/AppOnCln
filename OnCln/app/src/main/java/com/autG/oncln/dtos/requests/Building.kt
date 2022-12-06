package com.autG.oncln.dtos.requests

import com.autG.oncln.dtos.responses.Empresa

data class Building(
    val idPredio: Int,
    val nomePredio: String,
    val empresa: Empresa,
    val salas: ArrayList<SalaRequest>,
)
