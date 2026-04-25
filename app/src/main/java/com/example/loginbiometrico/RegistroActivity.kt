package com.example.loginbiometrico

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class RegistroActivity : AppCompatActivity() {

    private lateinit var etUser: EditText
    private lateinit var etPass: EditText
    private lateinit var btnRegistrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val prefs = getSharedPreferences("usuarios", MODE_PRIVATE)

        etUser = findViewById(R.id.etNuevoUsuario)
        etPass = findViewById(R.id.etNuevaPassword)
        btnRegistrar = findViewById(R.id.btnRegistrarCuenta)

        btnRegistrar.setOnClickListener {
            val editor = prefs.edit()
            editor.putString("user", etUser.text.toString())
            editor.putString("pass", etPass.text.toString())
            editor.apply()

            Toast.makeText(this, "Cuenta creada ✅", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}