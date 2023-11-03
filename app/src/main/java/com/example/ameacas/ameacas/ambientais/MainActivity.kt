package com.example.ameacas.ameacas.ambientais

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    var db: AmeacaDataBase? = null
    var ameacas: ListView? = null
    var adapter: AmeacaAdapter? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = AmeacaDataBase(baseContext)
        ameacas = findViewById(R.id.ameacas)
        adapter = AmeacaAdapter(baseContext, db!!)
        ameacas?.adapter = adapter

        ameacas?.setOnItemClickListener { _, _, _, id ->
            editarItem(id)
        }

        ameacas?.setOnItemLongClickListener { _, _, position, _ ->
            db?.removeAmeaca(adapter?.getItem(position) as Ameaca)
            adapter?.notifyDataSetChanged()
            true
        }
    }

    fun editarItem(id: Long) {
        val intent = Intent(baseContext, EditarAmeaca::class.java)
        val ameaca = db?.getAmeaca(id)
        intent.putExtra("ID", id)

        val lista: ArrayList<String> = ArrayList()
        lista.add(ameaca?.endereco.toString())
        lista.add(ameaca?.data.toString())
        lista.add(ameaca?.descricao.toString())

        intent.putStringArrayListExtra("DADOS", lista)
        startActivityForResult(intent, 2010)
    }

    fun adicionarAmeaca(view: View) {
        val intent = Intent(baseContext, AdicionarAmeacaActivity::class.java)
        intent.putExtra("ID", "")
        startActivityForResult(intent, 2020)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("MAIN_ACTIVITY", "voltou!!!!!!!!!")
        adapter?.notifyDataSetChanged()
    }
}