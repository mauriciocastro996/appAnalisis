
package com.example.loginbiometrico

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etUsuario: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnIngresar: Button
    private lateinit var btnBiometrico: Button
    private lateinit var tvCrearUsuario: TextView
    private lateinit var tvOlvido: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etUsuario = findViewById(R.id.etUsuario)
        etPassword = findViewById(R.id.etPassword)
        btnIngresar = findViewById(R.id.btnIngresar)
        btnBiometrico = findViewById(R.id.btnBiometrico)
        tvCrearUsuario = findViewById(R.id.tvCrearUsuario)
        tvOlvido = findViewById(R.id.tvOlvido)

        // BOTÓN INGRESAR
        btnIngresar.setOnClickListener {
            val intent = Intent(this@MainActivity, HomeActivity::class.java)
            startActivity(intent)
        }

        // BOTÓN BIOMÉTRICO
        btnBiometrico.setOnClickListener {
            Toast.makeText(this, "Ingreso con Huella / Face ID", Toast.LENGTH_SHORT).show()
        }

        // CREAR USUARIO
        tvCrearUsuario.setOnClickListener {
            val intent = Intent(this@MainActivity, RegistroActivity::class.java)
            startActivity(intent)
            }

        // OLVIDÓ CONTRASEÑA
        tvOlvido.setOnClickListener {
            Toast.makeText(this, "Recuperar contraseña", Toast.LENGTH_SHORT).show()
        }
    }
}
