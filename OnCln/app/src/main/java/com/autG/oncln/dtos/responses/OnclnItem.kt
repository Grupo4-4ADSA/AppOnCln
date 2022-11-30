package com.autG.oncln.dtos.responses

data class OnclnItem(
    val qrCode: String,
    val idCLNBox: Int,
    val ip: String,
    val sala: Room
)
