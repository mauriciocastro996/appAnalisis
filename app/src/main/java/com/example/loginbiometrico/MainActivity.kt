package com.example.loginbiometrico

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etUsuario: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnIngresar: Button
    private lateinit var tvCrearUsuario: TextView

    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prefs = getSharedPreferences("usuarios", MODE_PRIVATE)

        etUsuario = findViewById(R.id.etUsuario)
        etPassword = findViewById(R.id.etPassword)
        btnIngresar = findViewById(R.id.btnIngresar)
        tvCrearUsuario = findViewById(R.id.tvCrearUsuario)

        btnIngresar.setOnClickListener {
            login()
        }

        tvCrearUsuario.setOnClickListener {
            startActivity(Intent(this, RegistroActivity::class.java))
        }
    }

    private fun login() {
        val user = etUsuario.text.toString()
        val pass = etPassword.text.toString()

        val savedUser = prefs.getString("user", "")
        val savedPass = prefs.getString("pass", "")

        if (user == savedUser && pass == savedPass) {
            Toast.makeText(this, "Bienvenido 👋", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        } else {
            Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
        }
    }
}