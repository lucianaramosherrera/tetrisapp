package com.example.tetrisapp.view

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.tetrisapp.R
import com.example.tetrisapp.data.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ScoreActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        val db = AppDatabase.getDatabase(this)
        val scoreDao = db.scoreDao()
        val listView = findViewById<ListView>(R.id.lvScores)
        val btnVolver = findViewById<Button?>(R.id.btnVolver)

        CoroutineScope(Dispatchers.IO).launch {
            val scores = scoreDao.getAllScoresNow()
            val lista = scores.map { "${it.nombre} - ${it.puntos} (${it.fecha})" }
            runOnUiThread {
                listView.adapter = ArrayAdapter(this@ScoreActivity, android.R.layout.simple_list_item_1, lista)
            }
        }

        btnVolver?.setOnClickListener {
            finish()
        }
    }
}
