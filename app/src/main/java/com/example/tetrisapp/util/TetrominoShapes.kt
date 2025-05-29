package com.example.tetrisapp.util

object TetrominoShapes {



    val I = arrayOf(
        intArrayOf(0, 0, 0, 0),
        intArrayOf(1, 1, 1, 1),
        intArrayOf(0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0)
    )

    val O = arrayOf(
        intArrayOf(1, 1),
        intArrayOf(1, 1)
    )


    val T = arrayOf(
        intArrayOf(0, 1, 0),
        intArrayOf(1, 1, 1),
        intArrayOf(0, 0, 0)
    )


    val S = arrayOf(
        intArrayOf(0, 1, 1),
        intArrayOf(1, 1, 0),
        intArrayOf(0, 0, 0)
    )


    val Z = arrayOf(
        intArrayOf(1, 1, 0),
        intArrayOf(0, 1, 1),
        intArrayOf(0, 0, 0)
    )


    val J = arrayOf(
        intArrayOf(1, 0, 0),
        intArrayOf(1, 1, 1),
        intArrayOf(0, 0, 0)
    )


    val L = arrayOf(
        intArrayOf(0, 0, 1),
        intArrayOf(1, 1, 1),
        intArrayOf(0, 0, 0)
    )


    val shapes = mapOf(
        "I" to I,
        "O" to O,
        "T" to T,
        "S" to S,
        "Z" to Z,
        "J" to J,
        "L" to L
    )
}
