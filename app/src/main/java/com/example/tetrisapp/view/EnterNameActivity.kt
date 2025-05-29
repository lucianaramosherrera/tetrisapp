package com.example.tetrisapp.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tetrisapp.R

class EnterNameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_name)

        val etNombre = findViewById<EditText>(R.id.etNombre)
        val btnComenzar = findViewById<Button>(R.id.btnComenzar)

        btnComenzar.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            if (nombre.isNotEmpty()) {
                val intent = Intent(this, GameActivity::class.java)
                intent.putExtra("NOMBRE_JUGADOR", nombre)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "¡Ingresa tu nombre!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
