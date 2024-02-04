package com.example.viewbindingicm

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Resultado : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)

        // Recuperar el resultado del IMC del intent
        val imcResultado = intent.getFloatExtra("IMC_RESULTADO", 0.0f)

        // Acceder a tus TextView en Resultado.xml y mostrar el resultado
        val tvResultadoICM: TextView = findViewById(R.id.tvresultadoICM)
        tvResultadoICM.text = String.format("%.2f", imcResultado)

        // Puedes agregar lógica adicional para clasificar el IMC y mostrar un mensaje adecuado
        val tvPesoResultado: TextView = findViewById(R.id.tvpesoresultado)
        val tvResultadoTexto: TextView = findViewById(R.id.tvresultadotexto)

        // Ejemplo de clasificación del IMC y mensajes correspondientes
        val resultadoTexto: String
        val pesoResultado: String
        var colorResultado: Int = 0

        if (imcResultado < 18.5) {
            resultadoTexto = "Bajo peso"
            pesoResultado = "Bajo Peso"
            colorResultado = Color.YELLOW
        } else if (imcResultado < 24.9) {
            resultadoTexto = "Normal"
            pesoResultado = "Normal"
            colorResultado = Color.GREEN
        } else if (imcResultado < 29.9) {
            resultadoTexto = "Sobrepeso"
            pesoResultado = "Sobrepeso"
            colorResultado = Color.parseColor("#FFA500")  // Naranja
        } else {
            resultadoTexto = "Obesidad"
            pesoResultado = "Obesidad"
            colorResultado = Color.RED
        }

        tvResultadoTexto.text = "Estás en $resultadoTexto para tu peso y altura"
        tvPesoResultado.text = pesoResultado
        tvPesoResultado.setTextColor(colorResultado)

        // Configurar el botón "Re-Calcular" para regresar a MainActivity
        val btnRecalcular: Button = findViewById(R.id.btnrecalcular)
        btnRecalcular.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()  // Cierra la actividad actual y vuelve a la actividad anterior (MainActivity)
        }
    }
}
