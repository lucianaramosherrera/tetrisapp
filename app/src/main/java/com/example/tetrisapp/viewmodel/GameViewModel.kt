package com.example.tetrisapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tetrisapp.model.Board
import com.example.tetrisapp.model.Cell
import com.example.tetrisapp.model.Tetromino
import com.example.tetrisapp.util.Constants
import com.example.tetrisapp.util.TetrominoShapes

class GameViewModel : ViewModel() {
    private val _board = MutableLiveData(Board())
    val board: LiveData<Board> = _board

    private val _puntaje = MutableLiveData(0)
    val puntaje: LiveData<Int> = _puntaje

    private val _nivel = MutableLiveData(1)
    val nivel: LiveData<Int> = _nivel

    private val _gameOver = MutableLiveData(false)
    val gameOver: LiveData<Boolean> = _gameOver

    var currentTetromino: Tetromino? = null


    fun startGame() {
        _board.value = Board()
        _puntaje.value = 0
        _nivel.value = 1
        _gameOver.value = false
        currentTetromino = null
        spawnTetromino()
    }

    private fun createRandomTetromino(): Tetromino {
        val keys = TetrominoShapes.shapes.keys.toList()
        val type = keys.random()
        val shape = TetrominoShapes.shapes[type]!!.map { it.copyOf() }.toTypedArray()
        val color = Constants.TETROMINO_COLORS[type] ?: 0xFFFFFFFF.toInt()
        return Tetromino(shape, color)
    }

    private fun spawnTetromino() {
        currentTetromino = createRandomTetromino()
        currentTetromino?.row = 0
        currentTetromino?.col = (Constants.BOARD_WIDTH / 2) - 2

        updateBoardWithTetromino()

        val valid = isValidPosition(currentTetromino!!)
        Log.d("TETRIS", "¿La pieza inicial cabe?: $valid") // <-- DEBUG
        if (!valid) {
            _gameOver.value = true
        }
    }

    fun moveTetrominoLeft() {
        currentTetromino?.let {
            it.col -= 1
            if (!isValidPosition(it)) {
                it.col += 1
            } else {
                updateBoardWithTetromino()
            }
        }
    }

    fun moveTetrominoRight() {
        currentTetromino?.let {
            it.col += 1
            if (!isValidPosition(it)) {
                it.col -= 1
            } else {
                updateBoardWithTetromino()
            }
        }
    }

    fun moveTetrominoDown() {
        currentTetromino?.let {
            it.row += 1
            if (!isValidPosition(it)) {
                it.row -= 1
                fixTetromino()
                checkLines()
                if (_gameOver.value == false) {
                    spawnTetromino()
                }
            } else {
                updateBoardWithTetromino()
            }
        }
    }

    fun gameTick() {
        if (_gameOver.value == true) return
        moveTetrominoDown()
    }

    fun rotateTetrominoRight() {
        currentTetromino?.let {
            val oldShape = it.shape.map { it.copyOf() }.toTypedArray()
            it.rotateRight()
            if (!isValidPosition(it)) {
                it.shape = oldShape
            } else {
                updateBoardWithTetromino()
            }
        }
    }



    private fun fixTetromino() {
        val b = _board.value ?: return
        currentTetromino?.let { t ->
            for (i in t.shape.indices) {
                for (j in t.shape[i].indices) {
                    if (t.shape[i][j] != 0) {
                        val r = t.row + i
                        val c = t.col + j
                        if (r in 0 until Constants.BOARD_HEIGHT && c in 0 until Constants.BOARD_WIDTH) {
                            b.grid[r][c].filled = true
                            b.grid[r][c].color = t.color
                        }
                    }
                }
            }
        }
        updateBoardWithTetromino()
    }

    private fun checkLines() {
        val b = _board.value ?: return
        val rowsToClear = mutableListOf<Int>()
        for (row in 0 until Constants.BOARD_HEIGHT) {
            if (b.grid[row].all { it.filled }) {
                rowsToClear.add(row)
            }
        }
        if (rowsToClear.isNotEmpty()) {
            for (row in rowsToClear) {
                for (r in row downTo 1) {
                    for (c in 0 until Constants.BOARD_WIDTH) {
                        b.grid[r][c].filled = b.grid[r - 1][c].filled
                        b.grid[r][c].color = b.grid[r - 1][c].color
                    }
                }
                for (c in 0 until Constants.BOARD_WIDTH) {
                    b.grid[0][c] = Cell(false, 0)
                }
            }
            val lines = rowsToClear.size
            val score = if (lines == 1) {
                Constants.POINTS_PER_LINE
            } else {
                lines * lines * Constants.POINTS_PER_LINE
            }
            _puntaje.postValue((_puntaje.value ?: 0) + score)


            if ((_puntaje.value ?: 0) >= (_nivel.value ?: 1) * Constants.LEVEL_UP_SCORE) {
                _nivel.postValue((_nivel.value ?: 1) + 1)
            }
        }
    }

    private fun isValidPosition(t: Tetromino?): Boolean {
        t ?: return false
        val b = _board.value ?: return false
        for (i in t.shape.indices) {
            for (j in t.shape[i].indices) {
                if (t.shape[i][j] != 0) {
                    val r = t.row + i
                    val c = t.col + j
                    if (r < 0 || r >= Constants.BOARD_HEIGHT ||
                        c < 0 || c >= Constants.BOARD_WIDTH ||
                        b.grid[r][c].filled
                    ) {
                        return false
                    }
                }
            }
        }
        return true
    }

    private fun updateBoardWithTetromino() {
        val original = _board.value ?: return
        val tempGrid = Array(Constants.BOARD_HEIGHT) { row ->
            Array(Constants.BOARD_WIDTH) { col ->
                original.grid[row][col].copy()
            }
        }
        currentTetromino?.let { t ->
            for (i in t.shape.indices) {
                for (j in t.shape[i].indices) {
                    if (t.shape[i][j] != 0) {
                        val r = t.row + i
                        val c = t.col + j
                        if (r in 0 until Constants.BOARD_HEIGHT && c in 0 until Constants.BOARD_WIDTH) {
                            tempGrid[r][c].filled = true
                            tempGrid[r][c].color = t.color
                        }
                    }
                }
            }
        }
        _board.postValue(Board(Constants.BOARD_WIDTH, Constants.BOARD_HEIGHT, tempGrid))
    }
}
