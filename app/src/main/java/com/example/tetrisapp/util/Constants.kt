package com.example.tetrisapp.util

object Constants {
    const val BOARD_WIDTH = 10
    const val BOARD_HEIGHT = 20


    const val INITIAL_SPEED_MS = 500L
    const val MIN_SPEED_MS = 100L


    const val POINTS_PER_LINE = 10
    const val LEVEL_UP_SCORE = 5000


    val TETROMINO_COLORS = mapOf(
        "I" to 0xFF00FFFF.toInt(),
        "O" to 0xFFFFFF00.toInt(),
        "T" to 0xFF800080.toInt(),
        "S" to 0xFF00FF00.toInt(),
        "Z" to 0xFFFF0000.toInt(),
        "J" to 0xFF0000FF.toInt(),
        "L" to 0xFFFFA500.toInt()
    )
}
