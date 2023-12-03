package aoc2023

import org.junit.Test
import util.Util

class TestDay03 {

    val example = listOf(
        "467..114..",
        "...*......",
        "..35..633.",
        "......#...",
        "617*......",
        ".....+.58.",
        "..592.....",
        "......755.",
        "...$.*....",
        ".664.598.."
    )

    @Test
    fun `day 03 part 1`() {
        val partMatrix = Util.getInstance("aoc2023/day03")
            //val partMatrix = example
            .toPartMatrix()
        println("Part Matrix: $partMatrix")
        //val sum = example
        val sum = Util.getInstance("aoc2023/day03")
            .mapIndexed { i, x -> x.toEngineNumber(i) }
            .flatten()
            .map {
                println("EngineNumber: ${it.value}")
                it
            }
            .filter { it.isPartNumber(partMatrix.map { x -> x.pair }) }
            .map {
                println("PartNumber: ${it.value}")
                it
            }
            .sumOf { it.value }

        println(sum)
    }

    @Test
    fun `day 03 part 2`() {
        //val partMatrix = example
        val partMatrix = Util.getInstance("aoc2023/day03")
            .toPartMatrix()
            .filter { it.type == '*' }

        //val sum = example
        val sum = Util.getInstance("aoc2023/day03")
            .asSequence()
            .mapIndexed { i, x -> x.toEngineNumber(i) }
            .flatten()
            .map { en -> en.getPartNumbers(partMatrix).map { x -> x to en } }
            .flatten()
            .groupBy { it.first }
            .map { it.key to it.value.map { x -> x.second } }
            .filter { it.second.size == 2 }
            .sumOf { it.second[0].value * it.second[1].value }

        println("sum : $sum")
    }
}

private fun List<String>.toPartMatrix(): MutableList<Part> {
    val matrix: MutableList<Part> = mutableListOf()
    for (i in this.indices) {
        for (j in this[i].indices) {
            if (this[i][j] != '.' && !this[i][j].isDigit()) {
                matrix.add(Part(this[i][j], i to j))
            }
        }
    }
    return matrix
}

data class Part(val type: Char, val pair: Pair<Int, Int>) : NeighbouringThingy(pair.first, pair.second, pair.second)

private fun String.toEngineNumber(index: Int): MutableList<EngineNumber> {
    val engineNumbers: MutableList<EngineNumber> = mutableListOf()
    var currentNumber = ""
    for (j in this.indices) {
        if (this[j].isDigit()) {
            currentNumber += this[j]
            if (j == this.length - 1 || !this[j + 1].isDigit()) {
                engineNumbers.add(EngineNumber(index, j - currentNumber.length + 1, j, currentNumber.toInt()))
                currentNumber = ""
            }
        }
    }
    return engineNumbers
}

data class EngineNumber(override val index: Int, override val jStart: Int, override val jEnd: Int, val value: Int) :
    NeighbouringThingy(index, jStart, jEnd) {

    fun isPartNumber(partMatrix: List<Pair<Int, Int>>): Boolean {
        println("Number: ${this.value} neighbours: ${this.getNeighbours()}")
        return getNeighbours().intersect(partMatrix).isNotEmpty()
    }

    fun getPartNumbers(partMatrix: List<Part>): List<Part> {
        println("Number: ${this.value} neighbours: ${this.getNeighbours()}")
        return partMatrix
            .filter { this.getNeighbours().contains(it.pair) }
    }
}

open class NeighbouringThingy(open val index: Int, open val jStart: Int, open val jEnd: Int) {

    public fun getNeighbours(): List<Pair<Int, Int>> {
        val neighbours = mutableListOf<Pair<Int, Int>>()
        for (i in index - 1..index + 1) {
            for (j in jStart - 1..jEnd + 1) {
                neighbours.add(i to j)
            }
        }
        return neighbours
    }
}
