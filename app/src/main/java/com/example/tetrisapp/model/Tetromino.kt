package com.example.tetrisapp.model

data class Tetromino(
    var shape: Array<IntArray>,
    val color: Int,
    var row: Int = 0,
    var col: Int = 3
) {

    fun rotateRight() {
        shape = rotateMatrixRight(shape)
    }


    fun rotateLeft() {
        shape = rotateMatrixLeft(shape)
    }

    companion object {

        fun rotateMatrixRight(matrix: Array<IntArray>): Array<IntArray> {
            val n = matrix.size
            val m = matrix[0].size
            val ret = Array(m) { IntArray(n) }
            for (i in 0 until n)
                for (j in 0 until m)
                    ret[j][n - i - 1] = matrix[i][j]
            return ret
        }

        fun rotateMatrixLeft(matrix: Array<IntArray>): Array<IntArray> {
            val n = matrix.size
            val m = matrix[0].size
            val ret = Array(m) { IntArray(n) }
            for (i in 0 until n)
                for (j in 0 until m)
                    ret[m - j - 1][i] = matrix[i][j]
            return ret
        }
    }
}
