package com.example.viacep.data.model

data class AddressResponse(
    val cep: String?,
    val logradouro: String?,
    val complemento: String?,
    val bairro: String?,
    val localidade: String?,
    val uf: String?,
    val estado: String?,
    val regiao: String?
)

//{
//    "cep": "50710-395",
//    "logradouro": "Rua Dom Manoel da Costa",
//    "complemento": "até 529 - lado ímpar",
//    "unidade": "",
//    "bairro": "Madalena",
//    "localidade": "Recife",
//    "uf": "PE",
//    "estado": "Pernambuco",
//    "regiao": "Nordeste",
//    "ibge": "2611606",
//    "gia": "",
//    "ddd": "81",
//    "siafi": "2531"
//}