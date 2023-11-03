package com.example.ameacas.ameacas.ambientais

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.example.ameacas.ameacas.ambientais.StringUtils.Companion.haCamposInvalidos

class AdicionarAmeacaActivity : AppCompatActivity() {

    var endereco: EditText? = null
    var data: EditText? = null
    var descricao: EditText? = null
    var db: AmeacaDataBase? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adicionar_ameaca)

        endereco = findViewById(R.id.enderecoAddText)
        data = findViewById(R.id.dataAddText)
        descricao = findViewById(R.id.descricaoAddText)
        db = AmeacaDataBase(baseContext)
    }

    fun btAdicionar(view: View) {

        val enderecoTxt = endereco?.text.toString()
        val dataTxt = data?.text.toString()
        val descricaoTxt = descricao?.text.toString()

        if (haCamposInvalidos(enderecoTxt, dataTxt, descricaoTxt))
            return

        val ameaca = Ameaca(
            null,
            endereco = enderecoTxt,
            data = dataTxt,
            descricao = descricaoTxt
        )

        db?.addAmeaca(ameaca)
        finish()
    }
}