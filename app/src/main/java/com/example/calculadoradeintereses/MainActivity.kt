package com.example.calculadoradeintereses

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.log10
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    private lateinit var calculator: CompoundInterestCalculator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calculator = CompoundInterestCalculator()

        val spinnerCalculationType: Spinner = findViewById(R.id.spinner_calculation_type)
        val editTextPrincipal: EditText = findViewById(R.id.editText_principal)
        val editTextRate: EditText = findViewById(R.id.editText_rate)
        val editTextTime: EditText = findViewById(R.id.editText_time)
        val buttonCalculate: Button = findViewById(R.id.button_calculate)
        val buttonClear: Button = findViewById(R.id.button_clear)
        val textViewResult: TextView = findViewById(R.id.textView_result)

        val calculationTypes = arrayOf("Monto Final", "Interés", "Capital Inicial", "Tasa de Interés", "Tiempo")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, calculationTypes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCalculationType.adapter = adapter

        buttonCalculate.setOnClickListener {
            val principal = editTextPrincipal.text.toString().toDoubleOrNull()
            val rate = editTextRate.text.toString().toDoubleOrNull()?.div(100)
            val time = editTextTime.text.toString().toDoubleOrNull()
            val calculationType = spinnerCalculationType.selectedItem.toString()

            // Validar las entradas
            if ((principal == null || principal <= 0) && calculationType != "Capital Inicial") {
                editTextPrincipal.error = "Por favor, ingresa un valor válido"
                return@setOnClickListener
            }
            if ((rate == null || rate <= 0) && calculationType != "Tasa de Interés") {
                editTextRate.error = "Por favor, ingresa un valor válido"
                return@setOnClickListener
            }
            if ((time == null || time <= 0) && calculationType != "Tiempo") {
                editTextTime.error = "Por favor, ingresa un valor válido"
                return@setOnClickListener
            }

            // Realizar el cálculo correspondiente
            val result = when (calculationType) {
                "Monto Final" -> {
                    if (principal != null && rate != null && time != null) {
                        "Monto Final: ${calculator.calculateFinalAmount(principal, rate, time)}"
                    } else {
                        "Por favor, completa todos los campos necesarios"
                    }
                }
                "Interés" -> {
                    if (principal != null && rate != null && time != null) {
                        "Interés Compuesto: ${calculator.calculateCompoundInterest(principal, rate, time)}"
                    } else {
                        "Por favor, completa todos los campos necesarios"
                    }
                }
                "Capital Inicial" -> {
                    if (principal != null && rate != null && time != null) {
                        "Capital Inicial: ${calculator.calculatePrincipal(principal, rate, time)}"
                    } else {
                        "Por favor, completa todos los campos necesarios"
                    }
                }
                "Tasa de Interés" -> {
                    if (principal != null && rate != null && time != null) {
                        "Tasa de Interés: ${calculator.calculateRate(principal, rate, time)}"
                    } else {
                        "Por favor, completa todos los campos necesarios"
                    }
                }
                "Tiempo" -> {
                    if (principal != null && rate != null && time != null) {
                        "Tiempo: ${calculator.calculateTime(principal, rate, time)}"
                    } else {
                        "Por favor, completa todos los campos necesarios"
                    }
                }
                else -> "Selecciona una opción válida"
            }

            textViewResult.text = result
        }

        buttonClear.setOnClickListener {
            editTextPrincipal.text.clear()
            editTextRate.text.clear()
            editTextTime.text.clear()
            textViewResult.text = ""
        }
    }
}
