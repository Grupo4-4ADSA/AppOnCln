package com.autG.oncln.dtos.responses

data class Empresa(
    val ativa: Boolean,
    val cnpj: String,
    val dataAbertura: String,
    val email: String,
    val idEmpresa: Int,
    val razaoSocial: String,
    val telefone: String
)