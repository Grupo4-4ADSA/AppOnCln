package com.autG.oncln.dtos.requests

import com.autG.oncln.models.Predio

data class OnClnRequest(
    val qrCode: String,
    val ip: String,
    val sala: SalaRequest = SalaRequest(predio = Predio(2), name = "", floor = 0)
)
