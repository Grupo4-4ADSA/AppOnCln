package com.autG.oncln.dtos.requests

import com.autG.oncln.dtos.responses.RoomsItem

data class OnClnRequest(
    val qrCode: String,
    val ip: String,
    val sala: Sala
)