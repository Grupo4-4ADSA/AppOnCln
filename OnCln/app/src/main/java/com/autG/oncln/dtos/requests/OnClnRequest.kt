package com.autG.oncln.dtos.requests

import com.autG.oncln.dtos.responses.RoomsItem

data class OnClnRequest(
    val qrCode: String,
    val idClnbox: Int,
    val nameSala: String,
    val ip: String,
    val rooms: RoomsItem
)