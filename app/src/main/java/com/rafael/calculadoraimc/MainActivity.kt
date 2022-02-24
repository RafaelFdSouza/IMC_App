package com.rafael.calculadoraimc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.rafael.calculadoraimc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnCalculate.setOnClickListener {
            val weight = binding.etWeight.text.toString()
            val height = binding.etHeight.text.toString()

            if (validateInput(weight, height)) {
                val w = weight.toFloat()
                val h = height.toFloat() / 100
                val bmi = w / (h * h)
                displayResult(bmi)
            }
        }
    }

    private fun validateInput(weight: String?, height: String?): Boolean {

        return when {
            weight.isNullOrEmpty() -> {
                Toast.makeText(this, "Insira um valor ao Peso", Toast.LENGTH_LONG).show()
                return false
            }
            height.isNullOrEmpty() -> {
                Toast.makeText(this, "Insira um valor a Altura", Toast.LENGTH_LONG).show()
                return false
            }
            else -> {
                return true
            }

        }
    }

    private fun displayResult(bmi: Float) {
        val resultIndex = binding.tvResult
        val resultDescription = binding.tvResultDescription

        //bmi result formatted with two decimal places
        resultIndex.text = "%.2f".format(bmi)

        var resultText = " "
        var color = 0

        when {
            bmi < 18.50 -> {
                resultText = "Abaixo do normal"
                color = R.color.under_weight

            }
            bmi in 18.5..24.99 -> {
                resultText = "Normal"
                color = R.color.normal
            }
            bmi in 25.00..29.99 -> {
                resultText = "Sobrepeso"
                color = R.color.over_weight
            }
            bmi > 29.99 -> {
                resultText = "Obesidade"
                color = R.color.obese
            }

        }
        resultDescription.setTextColor(ContextCompat.getColor(this, color))
        resultDescription.text = resultText


    }

}





