package aoc2023


import aoc2023.day02.Game
import org.junit.Test
import util.Util

class TestDay02 {

    @Test
    fun `day 02 part 1`() {
        val acceptance = mapOf(
            "red" to 12,
            "green" to 13,
            "blue" to 14
        )
        val sum = Util.getInstance("aoc2023/day02")
            .map { it.toGame() }
            .filter { it.accepts(acceptance) }
            .sumOf { it.id }

        println(sum)
    }

    @Test
    fun `day 02 part 2`() {
        val acceptance = mapOf(
            "red" to 12,
            "green" to 13,
            "blue" to 14
        )
        val sum = Util.getInstance("aoc2023/day02")
            .map { it.toGame() }
            .map { it.power() }
            .sumOf { it }

        println(sum)
    }
}

private fun String.toGame(): Game {
    val splitColon = this.split(": ")
    val gamePlusID = splitColon[0]
    val gameId = gamePlusID.split(" ")[1].toInt()

    val allCubes = splitColon[1].split(", ")
        .map { it.split("; ") }
        .flatten()
    val cubesAndQuantityMap: MutableMap<String, Int> = mutableMapOf()

    allCubes
        .map { it.split(" ")[1] to it.split(" ")[0].toInt() }
        .map {
            if (cubesAndQuantityMap.containsKey(it.first)) {
                if (cubesAndQuantityMap[it.first]!! < it.second) {
                    cubesAndQuantityMap[it.first] = it.second
                }
            } else {
                cubesAndQuantityMap[it.first] = it.second
            }
        }

    return Game(id = gameId, cubesAndQuantityMap = cubesAndQuantityMap)
}
