package com.autG.oncln.models

import com.autG.oncln.dtos.requests.RoomsRequest
import com.autG.oncln.dtos.responses.Empresa

data class Building(
    val idPredio: Int,
    val nomePredio: String,
    val empresa: Empresa,
    val salas: ArrayList<RoomsRequest>,
)
