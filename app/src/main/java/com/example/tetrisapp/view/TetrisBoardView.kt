package com.example.tetrisapp.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.tetrisapp.model.Board
import com.example.tetrisapp.util.Constants

class TetrisBoardView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : View(context, attrs) {

    var board: Board? = null
        set(value) {
            field = value
            invalidate()
        }

    private val paintCell = Paint().apply { style = Paint.Style.FILL }
    private val paintGrid = Paint().apply {
        style = Paint.Style.STROKE
        color = Color.DKGRAY
        strokeWidth = 2f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val b = board ?: return

        val cellWidth = width / Constants.BOARD_WIDTH.toFloat()
        val cellHeight = height / Constants.BOARD_HEIGHT.toFloat()

        for (row in 0 until Constants.BOARD_HEIGHT) {
            for (col in 0 until Constants.BOARD_WIDTH) {
                val cell = b.grid[row][col]
                paintCell.color = if (cell.filled) cell.color else Color.BLACK
                canvas.drawRect(
                    col * cellWidth,
                    row * cellHeight,
                    (col + 1) * cellWidth,
                    (row + 1) * cellHeight,
                    paintCell
                )

                canvas.drawRect(
                    col * cellWidth,
                    row * cellHeight,
                    (col + 1) * cellWidth,
                    (row + 1) * cellHeight,
                    paintGrid
                )
            }
        }
    }
}
