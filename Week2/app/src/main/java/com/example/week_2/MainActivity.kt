package com.example.week_2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.widget.Button
import android.widget.EditText
class MainActivity : AppCompatActivity() {
    private lateinit var number1 : EditText
    private lateinit var number2 : EditText

    private lateinit var btnAdd : Button
    private lateinit var btnSubtract : Button
    private lateinit var btnDivide : Button
    private lateinit var btnMultiply : Button

    private lateinit var btnReset : Button

    private lateinit var result : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        number1 = findViewById(R.id.number1)
        number2 = findViewById(R.id.number2)

        btnAdd = findViewById(R.id.btnAdd)
        btnSubtract = findViewById(R.id.btnSubtract)
        btnMultiply = findViewById(R.id.btnMultiply)
        btnDivide = findViewById(R.id.btnDivide)

        btnReset = findViewById(R.id.btnReset)

        result = findViewById(R.id.result)

        btnAdd.setOnClickListener {
            calculate("+")
        }

        btnSubtract.setOnClickListener {
            calculate("-")
        }

        btnMultiply.setOnClickListener {
            calculate("*")
        }

        btnDivide.setOnClickListener {
            calculate("/")
        }

        btnReset.setOnClickListener {
            number1.text.clear()
            number2.text.clear()
            result.text = getString(R.string.result)
        }
    }

    fun getNumber(editText: EditText, fieldName: String) : Double? {
        val text = editText.text.toString().trim()
        val number = text.toDoubleOrNull()

        if(text.isEmpty()) {
            if(fieldName == "First number") {
                result.text = getString(R.string.first_number_empty)
            } else {
                result.text = getString(R.string.second_number_empty)
            }

            return null
        }
        return number
    }

    fun calculate(operator: String) {
        val a = getNumber(number1, "First number") ?: return
        val b = getNumber(number2, "Second number") ?: return

        if(operator == "/" && b == 0.0) {
            result.text = getString(R.string.divide_by_zero)
            return
        }

        val answer = when (operator) {
            "+" -> add(a, b)

            "-" -> subtract(a, b)

            "*" -> multiply(a, b)

            "/" -> divide(a, b)

            else -> {
                result.text = getString(R.string.unknown_operation)
                return
            }
        }

        result.text = getString(R.string.result_value, answer)

    }

    fun add(a: Double, b: Double): Double {
        return a + b
    }

    // Subtraction
    fun subtract(a: Double, b: Double): Double {
        return a - b
    }

    // Multiplication
    fun multiply(a: Double, b: Double): Double {
        return a * b
    }

    // Division
    fun divide(a: Double, b: Double): Double {
        return a / b
    }

}