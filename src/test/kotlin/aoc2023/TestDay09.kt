package aoc2023

import org.junit.Test
import util.Util

class TestDay09 {

    val example = listOf(
        "0 3 6 9 12 15",
        "1 3 6 10 15 21",
        "10 13 16 21 30 45",
    )

    @Test
    fun `test day 09 part 1`() {
        val instance = Util.getInstance("aoc2023/day09").toListsOfInt()
        val exampleInstance = example.toListsOfInt()

        val exampleSum = exampleInstance.computePrediction().sum()
        val result = instance.computePrediction().sum()

        println("Example: $exampleSum")
        println("Result: $result")

    }

    @Test
    fun `test day 09 part 2`() {
        val instance = Util.getInstance("aoc2023/day09").toListsOfInt()
        val exampleInstance = example.toListsOfInt()

        val exampleSum = exampleInstance.computePreviousValue().sum()
        val result = instance.computePreviousValue().sum()

        println("Example: $exampleSum")
        println("Result: $result")

    }
}

private fun List<List<Int>>.computePrediction(): List<Int> {
    return this.map { it.computePrediction() }
}

private fun List<Int>.computePrediction(): Int {
    return if (this.all { it == this.first() }) {
        this.first()
    } else {
        val diffs = this.getDiffs()
        this.last() + diffs.computePrediction()
    }
}

private fun List<List<Int>>.computePreviousValue(): List<Int> {
    return this.map { it.computePreviousValue() }
}

private fun List<Int>.computePreviousValue(): Int {
    return if (this.all { it == this.first() }) {
        this.first()
    } else {
        val diffs = this.getDiffs()
        this.first() - diffs.computePreviousValue()
    }
}

private fun List<Int>.getDiffs(): List<Int> {
    val result = mutableListOf<Int>()
    for (i in 1 until this.size) {
        result.add(this[i] - this[i - 1])
    }
    return result
}

private fun List<String>.toListsOfInt(): List<List<Int>> {
    return this.map { it.split(" ").map { x -> x.toInt() } }
}
