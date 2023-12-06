package aoc2023

import org.junit.Test

class TestDay06 {

    private val instance = listOf(
        Race(50, 242),
        Race(74, 1017),
        Race(86, 1691),
        Race(85, 1252)
    )

    private val instanceForReal = listOf(
        Race(50748685, 242101716911252),
    )

    private val example = listOf(
        Race(7, 9),
        Race(15, 40),
        Race(30, 200),
    )

    private val exampleForReal = listOf(
        Race(71530, 940200),
    )


    @Test
    fun `day 06 part 1`() {
        val result = instance.map { it.toPossibilities() }
            .map {
                println(it)
                it
            }
            .reduce { a, b -> a * b }
        println("Result: $result")
    }

    @Test
    fun `day 06 part 2`() {
        val result = instanceForReal.map { it.toPossibilities() }
            .map {
                println(it)
                it
            }
            .reduce { a, b -> a * b }
        println("Result: $result")
    }

}

private fun Race.toPossibilities(): Int {
    var result = 0;
    for (chargeTime in 1 until this.time) {
        val distanceForThisHypothesis : Long = ((this.time.toLong() - chargeTime) * chargeTime)
        if (distanceForThisHypothesis > this.distance) {
            result++
        } else if (result > 0) {
            return result
        }
    }
    return result
}
