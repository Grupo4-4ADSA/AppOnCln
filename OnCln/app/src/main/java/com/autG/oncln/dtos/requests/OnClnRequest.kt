package com.autG.oncln.dtos.requests

import com.autG.oncln.models.Predio

data class OnClnRequest(
    val idCLNBox: Int = 1,
    val qrCode: String,
    val ip: String,
    val sala: SalaRequest = SalaRequest(predio = Predio(2), name = "", floor = 0)
)

//Todo debuggar a register e ver como ta sendo preenchido e usar o endpoint que o jonas ainda vai fazer