package com.example.appfinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var listaProductos = emptyList<Producto>()

        val dataBase = AppDataBase.getDatabase(this)

        dataBase.productosDao().getAll().observe(this, Observer {
            listaProductos = it

            val adapter = ProductosAdapter(this, listaProductos)

            lista.adapter = adapter
        })

        lista.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, ProductoActivity::class.java)
            intent.putExtra("id", listaProductos[position].idProducto)
            startActivity(intent)
        }
        add_fab.setOnClickListener{
            val intent = Intent(this, NuevoProducto::class.java)
            startActivity(intent)
        }

    }
}