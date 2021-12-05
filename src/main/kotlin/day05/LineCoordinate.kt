package day05

import kotlin.math.abs

data class LineCoordinate(val x1: Int, val y1: Int, val x2: Int, val y2: Int) {
    fun isNotDiagonal(): Boolean {
        return (x1 == x2 || y1 == y2)
    }
}

object CoordinateMapper {
    fun String.toLineCoordinate(): LineCoordinate {
        val split = this.split(" -> ")
        val firstPointCoord = split[0].split(",")
        val lastPointCoord = split[1].split(",")
        return LineCoordinate(
            firstPointCoord[0].toInt(), firstPointCoord[1].toInt(),
            lastPointCoord[0].toInt(), lastPointCoord[1].toInt()
        )
    }

    fun checkMatrix(lines: List<LineCoordinate>, useDiagonal: Boolean = false): Int {
        val matrix = mutableListOf<MutableList<Int>>()
        val maxValue = getMaxValueFor(lines)
        for (i in 0..maxValue) {
            matrix.add(mutableListOf())
            for (j in 0..maxValue) {
                matrix.last().add(0)
            }
        }
        lines.map { checkLine(matrix, it, useDiagonal) }
        return matrix.flatten().filter { it >= 2 }.count()

    }

    private fun checkLine(matrix: MutableList<MutableList<Int>>, line: LineCoordinate, useDiagonal: Boolean) {
        if (line.isNotDiagonal() || useDiagonal) {
            var x = line.x1
            var y = line.y1
            while (x != line.x2 || y != line.y2) {
                matrix[x][y]++
                if (x != line.x2) {
                    x += (line.x2 - line.x1) / abs(line.x2 - line.x1)
                }
                if (y != line.y2) {
                    y += (line.y2 - line.y1) / abs(line.y2 - line.y1)
                }
            }
            matrix[line.x2][line.y2]++
        }
    }

    private fun getMaxValueFor(lines: List<LineCoordinate>): Int {
        return lines.flatMap { listOf(it.x1, it.y1, it.x2, it.y2) }
            .maxByOrNull { it }!!

    }
}