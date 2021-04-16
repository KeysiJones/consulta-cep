package com.kdev.consultacep.model

data class Endereco(
    val cep: String,
    val logradouro: String,
    val complemento: String,
    val bairro: String = "São José",
    val localidade: String,
    val uf: String,
    val ibge: String,
    val gia: String,
    val ddd: String,
    val siafi: String
)