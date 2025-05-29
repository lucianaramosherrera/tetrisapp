package com.example.tetrisapp.model

data class Board(
    val width: Int = 10,
    val height: Int = 20,
    val grid: Array<Array<Cell>> = Array(20) { Array(10) { Cell() } }
)
