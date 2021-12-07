package day07

import kotlin.math.abs
import kotlin.math.min

object CrabMoves {

    fun sumOfMoves(crabPositions: List<Int>): Int {
        val mean = (crabPositions.reduce { a, b -> a + b } / crabPositions.size)
        var sumOfMoves = crabPositions.map { abs(it - mean) }.reduce { a, b -> a + b }
        for (i in 0..mean / 2) {
            sumOfMoves = min(crabPositions.map { abs(it - (mean + i)) }.reduce { a, b -> a + b }, sumOfMoves)
            sumOfMoves = min(crabPositions.map { abs(it - (mean - i)) }.reduce { a, b -> a + b }, sumOfMoves)
        }
        return sumOfMoves
    }

    fun sumOfMoves2(crabPositions: List<Int>): Int {
        val mean = (crabPositions.reduce { a, b -> a + b } / crabPositions.size)
        var sumOfMoves = crabPositions.map { getMoveCost(abs(it - mean)) }.reduce { a, b -> a + b }
        for (i in 0..mean / 2) {
            sumOfMoves = min(crabPositions.map { getMoveCost(abs(it - (mean + i))) }.reduce { a, b -> a + b }, sumOfMoves)
            sumOfMoves = min(crabPositions.map { getMoveCost(abs(it - (mean - i))) }.reduce { a, b -> a + b }, sumOfMoves)
        }
        return sumOfMoves
    }

    private fun getMoveCost(move: Int): Int {
        return ((move + 1) * move) / 2
    }
}