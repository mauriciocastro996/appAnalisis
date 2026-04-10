
package com.example.loginbiometrico

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
    private lateinit var btnGoogleLogin: Button
    private lateinit var btnFacebookLogin: Button
    private lateinit var tvCrearUsuario: TextView
    private lateinit var tvOlvido: TextView
    private lateinit var tvErrorMessage: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeViews()
        setupValidation()
        setupEventListeners()
    }

    private fun initializeViews() {
        etUsuario = findViewById(R.id.etUsuario)
        etPassword = findViewById(R.id.etPassword)
        btnIngresar = findViewById(R.id.btnIngresar)
        btnBiometrico = findViewById(R.id.btnBiometrico)
        btnGoogleLogin = findViewById(R.id.btnGoogleLogin)
        btnFacebookLogin = findViewById(R.id.btnFacebookLogin)
        tvCrearUsuario = findViewById(R.id.tvCrearUsuario)
        tvOlvido = findViewById(R.id.tvOlvido)
        tvErrorMessage = findViewById(R.id.tvErrorMessage)
    }

    private fun setupValidation() {
        // Text change listeners for real-time validation
        etUsuario.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                clearError()
            }
        })

        etPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                clearError()
            }
        })
    }

    private fun setupEventListeners() {
        // BOTÓN INGRESAR
        btnIngresar.setOnClickListener {
           // if (validateLoginForm()) {
                // TODO: Implement actual authentication API call
                // POST /api/auth/login with username and password
                println("API CALL: POST /api/auth/login")
                println("Username: ${etUsuario.text}")
                println("Password: [HIDDEN]")
                
                val intent = Intent(this@MainActivity, HomeActivity::class.java)
                startActivity(intent)
                finish()
           // }
        }

        // BOTÓN BIOMÉTRICO
        btnBiometrico.setOnClickListener {
            Toast.makeText(this, "Ingreso con Huella / Face ID", Toast.LENGTH_SHORT).show()
            // TODO: Implement biometric authentication
        }

        // BOTÓN GOOGLE LOGIN
        btnGoogleLogin.setOnClickListener {
            Toast.makeText(this, "Iniciando sesión con Google...", Toast.LENGTH_SHORT).show()
            // TODO: Implement Google Sign-In
            // POST /api/auth/google with Google token
            println("API CALL: POST /api/auth/google")
        }

        // BOTÓN FACEBOOK LOGIN
        btnFacebookLogin.setOnClickListener {
            Toast.makeText(this, "Iniciando sesión con Facebook...", Toast.LENGTH_SHORT).show()
            // TODO: Implement Facebook Login
            // POST /api/auth/facebook with Facebook token
            println("API CALL: POST /api/auth/facebook")
        }

        // CREAR USUARIO
        tvCrearUsuario.setOnClickListener {
            val intent = Intent(this@MainActivity, RegistroActivity::class.java)
            startActivity(intent)
        }

        // OLVIDÓ CONTRASEÑA
        tvOlvido.setOnClickListener {
            Toast.makeText(this, "Recuperar contraseña", Toast.LENGTH_SHORT).show()
            // TODO: Navigate to password recovery
            // POST /api/auth/password-recovery with email
            println("API CALL: POST /api/auth/password-recovery")
        }
    }

    private fun validateLoginForm(): Boolean {
        val username = etUsuario.text.toString().trim()
        val password = etPassword.text.toString().trim()

        when {
            username.isEmpty() -> {
                showError("Por favor ingrese su usuario")
                etUsuario.requestFocus()
                return false
            }
            password.isEmpty() -> {
                showError("Por favor ingrese su contraseña")
                etPassword.requestFocus()
                return false
            }
            username.length < 3 -> {
                showError("El usuario debe tener al menos 3 caracteres")
                etUsuario.requestFocus()
                return false
            }
            password.length < 6 -> {
                showError("La contraseña debe tener al menos 6 caracteres")
                etPassword.requestFocus()
                return false
            }
            else -> return true
        }
    }

    private fun showError(message: String) {
        tvErrorMessage.text = message
        tvErrorMessage.visibility = TextView.VISIBLE
    }

    private fun clearError() {
        tvErrorMessage.text = ""
        tvErrorMessage.visibility = TextView.GONE
    }
}
