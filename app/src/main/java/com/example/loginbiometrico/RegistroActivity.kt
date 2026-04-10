
package com.example.loginbiometrico

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class RegistroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etCorreo = findViewById<EditText>(R.id.etCorreo)
        val etUsuario = findViewById<EditText>(R.id.etNuevoUsuario)
        val etPass = findViewById<EditText>(R.id.etNuevaPassword)
        val etConfirmar = findViewById<EditText>(R.id.etConfirmarPassword)

        val btnRegistrar = findViewById<Button>(R.id.btnRegistrarCuenta)
        val btnVolver = findViewById<Button>(R.id.btnVolverLogin)

        btnRegistrar.setOnClickListener {

            if(
                etNombre.text.isEmpty() ||
                etCorreo.text.isEmpty() ||
                etUsuario.text.isEmpty() ||
                etPass.text.isEmpty() ||
                etConfirmar.text.isEmpty()
            ){
                Toast.makeText(this,"Complete todos los campos",Toast.LENGTH_SHORT).show()
            }

            else if(etPass.text.toString() != etConfirmar.text.toString()){
                Toast.makeText(this,"Las contraseñas no coinciden",Toast.LENGTH_SHORT).show()
            }

            else{
                Toast.makeText(this,"Cuenta creada correctamente",Toast.LENGTH_SHORT).show()
            }

        }

        btnVolver.setOnClickListener {
            finish()
        }

    }
}

