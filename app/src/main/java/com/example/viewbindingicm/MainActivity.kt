package com.example.viewbindingicm

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private lateinit var tvAltura: TextView
    private lateinit var rsAltura: RangeSlider
    private lateinit var tvPeso: TextView
    private lateinit var fabAddPeso: FloatingActionButton
    private lateinit var fabRemovePeso: FloatingActionButton
    private lateinit var tvEdad: TextView
    private lateinit var fabAddEdad: FloatingActionButton
    private lateinit var fabRemoveEdad: FloatingActionButton
    private lateinit var btnCalcular: Button

    private var altura: Float = 0.0f
    private var peso: Int = 0
    private var edad: Int = 0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar vistas
        tvAltura = findViewById(R.id.tvaltura)
        rsAltura = findViewById(R.id.rsaltura)
        tvPeso = findViewById(R.id.tvpeso)
        fabAddPeso = findViewById(R.id.fabaddpeso)
        fabRemovePeso = findViewById(R.id.fabremovepeso)
        tvEdad = findViewById(R.id.tvedad)
        fabAddEdad = findViewById(R.id.fabaddedad)
        fabRemoveEdad = findViewById(R.id.fabremoveedad)
        btnCalcular = findViewById(R.id.btncalcular)

        // Configurar listener para la altura
        rsAltura.addOnChangeListener { _, value, _ ->
            val format = DecimalFormat("#.##")
            altura = value
            tvAltura.text = "${format.format(value)} cm"
        }

        // Configurar listeners para el peso
        fabAddPeso.setOnClickListener {
            peso++
            actualizarPeso()
        }

        fabRemovePeso.setOnClickListener {
            if (peso > 0) {
                peso--
                actualizarPeso()
            }
        }

        // Configurar listeners para la edad
        fabAddEdad.setOnClickListener {
            edad++
            actualizarEdad()
        }

        fabRemoveEdad.setOnClickListener {
            if (edad > 0) {
                edad--
                actualizarEdad()
            }
        }

        // Configurar listener para el botón de calcular
        btnCalcular.setOnClickListener {
            calcularIMC()
        }
    }

    private fun actualizarPeso() {
        tvPeso.text = "$peso kg"
    }

    private fun actualizarEdad() {
        tvEdad.text = "$edad"
    }

    private fun calcularIMC() {
        // Realizar el cálculo del IMC según la fórmula
        val alturaEnMetros = altura / 100
        val imc = peso / (alturaEnMetros * alturaEnMetros)

        // Crear un intent para abrir la actividad Resultado
        val intent = Intent(this, Resultado::class.java)

        // Pasar el resultado del IMC como extra al intent
        intent.putExtra("IMC_RESULTADO", imc)

        // Iniciar la actividad Resultado
        startActivity(intent)
    }
}
