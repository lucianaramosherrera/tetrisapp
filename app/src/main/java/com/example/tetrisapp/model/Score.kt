package com.example.tetrisapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "scores")
data class Score(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String,
    val puntos: Int,
    val fecha: String
)
