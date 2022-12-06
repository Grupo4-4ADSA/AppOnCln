package com.autG.oncln.dtos.responses

data class EquipmentsResponse(
    val idEquipamento: Int,
    val registro: Registers?,
    val clnBox: OnclnItem,
    val instalacao: String,
    val nome: String,
    val porta: Int,
    val potencia: Int,
    val qtdEquipamento: Int,
    val tipo: String,
    val vidaUtil: Int,
    private val onClick: (id: Int, nameRoom: String) -> Unit,
    private val delete: (idRoom: Int, nameRoom: String) -> Unit
)