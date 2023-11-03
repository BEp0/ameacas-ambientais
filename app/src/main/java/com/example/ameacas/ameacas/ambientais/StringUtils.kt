package com.example.ameacas.ameacas.ambientais

class StringUtils {
    companion object {
        fun haCamposInvalidos(endereco: String, data: String, descricao: String) = haCamposVazios(endereco, data, descricao) || dataInvalida(data)

        private fun haCamposVazios(endereco: String, data: String, descricao: String) =
            endereco.isBlank() || data.isBlank() || descricao.isBlank()

        private fun dataInvalida(data: String) =
            !((data.length == 10) && (data.substring(2, 3) == "/") && (data.substring(5, 6) == "/"))
    }
}