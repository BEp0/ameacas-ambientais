package com.example.ameacas.ameacas.ambientais

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.example.ameacas.ameacas.ambientais.StringUtils.Companion.haCamposInvalidos

class EditarAmeaca : AppCompatActivity() {

    var endereco: EditText? = null
    var data: EditText? = null
    var descricao: EditText? = null
    var db: AmeacaDataBase? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_ameaca)

        endereco = findViewById(R.id.enderecoEditText2)
        data = findViewById(R.id.dataEditText)
        descricao = findViewById(R.id.descricaoEditText)
        db = AmeacaDataBase(baseContext)

        val lista = intent.getStringArrayListExtra("DADOS")

        endereco?.setText(lista?.get(0))
        data?.setText(lista?.get(1))
        descricao?.setText(lista?.get(2))
    }

    fun btEditar(view: View) {

        val enderecoTxt = endereco?.text.toString()
        val dataTxt = data?.text.toString()
        val descricaoTxt = descricao?.text.toString()

        if (haCamposInvalidos(enderecoTxt, dataTxt, descricaoTxt))
            return

        val id = intent.getLongExtra("ID", 0L)
        val ameaca = db?.getAmeaca(id)

        ameaca?.endereco = enderecoTxt
        ameaca?.data = dataTxt
        ameaca?.descricao = descricaoTxt

        db?.updateAmeaca(ameaca)
        finish()
    }
}