package com.example.loginbiometrico

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChatActivity : AppCompatActivity() {

    private lateinit var tvChatLog: TextView
    private lateinit var etMensaje: EditText
    private lateinit var btnEnviar: Button // CAMBIADO A BUTTON PARA COINCIDIR CON XML
    private lateinit var scrollView: ScrollView
    private lateinit var materia: String

    // CONFIGURACIÓN DE LA IA
    private val model = GenerativeModel(
        modelName = "gemini-1.5-flash",
        apiKey = "AIzaSyAl0HiJtUjrJ3p7VdZc8gG7IbP6zUo_vJY" // PO // N TU LLAVE REAL AQUÍ
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        // Inicializar vistas con los IDs del XML
        tvChatLog = findViewById(R.id.tvChatLog)
        etMensaje = findViewById(R.id.etMensaje)
        btnEnviar = findViewById(R.id.btnEnviar)
        scrollView = findViewById(R.id.scrollView)

        materia = intent.getStringExtra("materia") ?: "General"

        // Configurar Título
        findViewById<TextView>(R.id.tvTitulo).text = "Tutor de $materia 🤖"

        // Mensaje Inicial
        tvChatLog.text = "🤖 Bot: ¡Hola! Soy tu asistente de $materia. ¿En qué puedo ayudarte?\n"

        btnEnviar.setOnClickListener {
            val texto = etMensaje.text.toString().trim()
            if (texto.isNotEmpty()) {
                preguntarALaIA(texto)
            }
        }
    }

    private fun preguntarALaIA(preguntaUsuario: String) {
        // Mostrar mensaje del usuario
        tvChatLog.append("\n🧑 Tú: $preguntaUsuario\n")
        etMensaje.text.clear()

        // Estado de espera
        btnEnviar.isEnabled = false
        tvChatLog.append("🤖 Bot está analizando...\n")
        scrollAlFinal()

        lifecycleScope.launch(Dispatchers.Main) {
            try {
                // Instrucción de contexto
                val promptFinal = "Actúa como un profesor experto en $materia. " +
                        "Responde de forma clara: $preguntaUsuario"

                // Llamada a la IA en hilo secundario
                val respuestaIA = withContext(Dispatchers.IO) {
                    model.generateContent(promptFinal)
                }

                // Quitar el "analizando" y poner la respuesta
                val textoLimpio = tvChatLog.text.toString().replace("🤖 Bot está analizando...\n", "")
                tvChatLog.text = textoLimpio
                tvChatLog.append("🤖 Bot: ${respuestaIA.text}\n")

            } catch (e: Exception) {
                // En caso de error, limpiar el "analizando" y avisar
                val textoError = tvChatLog.text.toString().replace("🤖 Bot está analizando...\n", "")
                tvChatLog.text = textoError
                tvChatLog.append("❌ Error: Verifica tu conexión o tu API Key.\n")
            } finally {
                btnEnviar.isEnabled = true
                scrollAlFinal()
            }
        }
    }

    private fun scrollAlFinal() {
        scrollView.post {
            scrollView.fullScroll(View.FOCUS_DOWN)
        }
    }
}