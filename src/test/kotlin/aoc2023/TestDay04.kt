package aoc2023

import aoc2023.day04.Card
import org.junit.Test
import util.Util

class TestDay04 {

    private val example = listOf(
        "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53",
        "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19",
        "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1",
        "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83",
        "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36",
        "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11",
    )

    @Test
    fun `day 04 part 1`() {
        val sum = Util.getInstance("aoc2023/day04")
            //val sum = example
            .map { it.toCard() }
            .sumOf { it.getScore() }

        println("Sum: $sum")
    }

    @Test
    fun `day 04 part 2`() {

        val input =   Util.getInstance("aoc2023/day04")
        val cardMap = input.map { it.toCard() }.associateBy { it.id }
        val count = input
            .map { it.toCard() }
            .flatMap { it.toSubsequentCards(cardMap) }
            .count()

        println("Count = $count")
    }

    private fun String.toCard(): Card {
        val id = this.split(":")[0].split(" ").map { it.trim() }
            .filter { it.isNotEmpty() }[1].toInt()
        val winningNumbers = this.split(":")[1]
            .split(" | ")[0]
            .split(" ")
            .map { it.trim() }
            .filter { it.isNotEmpty() }
            .map { it.toInt() }
        val myNumbers = this.split(":")[1]
            .split(" | ")[1]
            .split(" ")
            .map { it.trim() }
            .filter { it.isNotEmpty() }
            .map { it.toInt() }
        return Card(id, winningNumbers, myNumbers)
    }


}
