package com.example.tetrisapp.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.tetrisapp.R
import com.example.tetrisapp.viewmodel.GameViewModel

class GameActivity : AppCompatActivity() {

    private lateinit var tetrisBoardView: TetrisBoardView
    private lateinit var tvPuntaje: TextView
    private lateinit var tvNivel: TextView
    private lateinit var btnIzquierda: Button
    private lateinit var btnDerecha: Button
    private lateinit var btnAbajo: Button
    private lateinit var btnRotar: Button

    private val gameViewModel: GameViewModel by viewModels()
    private val handler = Handler(Looper.getMainLooper())
    private var running = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        tetrisBoardView = findViewById(R.id.tetrisBoard)
        tvPuntaje = findViewById(R.id.tvPuntaje)
        tvNivel = findViewById(R.id.tvNivel)
        btnIzquierda = findViewById(R.id.btnIzquierda)
        btnDerecha = findViewById(R.id.btnDerecha)
        btnAbajo = findViewById(R.id.btnAbajo)
        btnRotar = findViewById(R.id.btnRotar)


        gameViewModel.board.observe(this) { board ->
            tetrisBoardView.board = board
        }
        gameViewModel.puntaje.observe(this) { score ->
            tvPuntaje.text = "Puntaje: $score"
        }
        gameViewModel.nivel.observe(this) { nivel ->
            tvNivel.text = "Nivel: $nivel"
        }
        gameViewModel.gameOver.observe(this) { isOver ->
            if (isOver) {
                running = false
                Toast.makeText(this, "Juego terminado", Toast.LENGTH_SHORT).show()
                // Aquí podrías mostrar un diálogo, guardar el puntaje, etc.
                finish()
            }
        }


        btnIzquierda.setOnClickListener { gameViewModel.moveTetrominoLeft() }
        btnDerecha.setOnClickListener { gameViewModel.moveTetrominoRight() }
        btnAbajo.setOnClickListener { gameViewModel.moveTetrominoDown() }
        btnRotar.setOnClickListener { gameViewModel.rotateTetrominoRight() }


        gameViewModel.startGame()
        startGameLoop()
    }


    private fun startGameLoop() {
        running = true
        handler.post(object : Runnable {
            override fun run() {
                if (running) {
                    gameViewModel.gameTick()

                    handler.postDelayed(this, 500)
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        running = false
        handler.removeCallbacksAndMessages(null)
    }
}
