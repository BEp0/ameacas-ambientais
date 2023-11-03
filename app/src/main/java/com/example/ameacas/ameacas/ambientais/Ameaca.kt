package com.example.ameacas.ameacas.ambientais

class Ameaca(
    val id: Long?,
    var endereco: String,
    var data: String,
    var descricao: String,
) {

    override fun toString(): String {
        return "$endereco $data $descricao"
    }
}