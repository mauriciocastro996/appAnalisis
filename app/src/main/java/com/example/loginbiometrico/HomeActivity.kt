package com.example.loginbiometrico

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        findViewById<MaterialButton>(R.id.btnMatematicas)
            .setOnClickListener { abrirChat("Matemáticas") }

        findViewById<MaterialButton>(R.id.btnHistoria)
            .setOnClickListener { abrirChat("Historia") }

        findViewById<MaterialButton>(R.id.btnCiencia)
            .setOnClickListener { abrirChat("Ciencia") }

        findViewById<MaterialButton>(R.id.btnLenguaje)
            .setOnClickListener { abrirChat("Lenguaje") }

        findViewById<MaterialButton>(R.id.btnFisica)
            .setOnClickListener { abrirChat("Física") }

        findViewById<MaterialButton>(R.id.btnOtros)
            .setOnClickListener { abrirChat("Otros") }
    }

    private fun abrirChat(materia: String) {
        val intent = Intent(this, ChatActivity::class.java)
        intent.putExtra("materia", materia)
        startActivity(intent)
    }
}