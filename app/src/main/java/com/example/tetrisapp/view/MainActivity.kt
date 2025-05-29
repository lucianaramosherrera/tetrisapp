package com.example.tetrisapp.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tetrisapp.R
import com.example.tetrisapp.view.EnterNameActivity
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnJugar).setOnClickListener {
            val intent = Intent(this, EnterNameActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnPuntajes).setOnClickListener {
            val intent = Intent(this, ScoreActivity::class.java)
            startActivity(intent)
        }
    }
}
