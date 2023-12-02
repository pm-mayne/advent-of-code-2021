package aoc2023

import org.junit.Test
import util.Util

class TestDay01 {

    @Test
    fun `day 01 part 1`() {
        val sum = Util.getInstance("aoc2023/day01")
            .filter { it.isNotEmpty() }
            .map { it.filter { x -> x.isDigit() } }
            .map {
                when (it.length) {
                    1 -> it + it
                    2 -> it
                    else -> it[0] + "" + it[it.length - 1]
                }
            }
            .sumOf { it.toInt() }

        println(sum)
    }

    @Test
    fun `day 01 part 2`() {
        val sum = Util.getInstance("aoc2023/day01")
            .asSequence()
            .filter { it.isNotEmpty() }
            .map { it.toDigits().filter { x -> x.isDigit() } }
            .map {
                when (it.length) {
                    1 -> it + it
                    2 -> it
                    else -> it[0] + "" + it[it.length - 1]
                }
            }
            .sumOf { it.toInt() }

        println(sum)

    }

    @Test
    fun `test toDigits part 2`() {
        val toDigits = "actwothree4eerrtsrtthree7ty9".toDigits()
            .filter { x -> x.isDigit() }
        println(toDigits)
        //assert(toDigits == "234379")
        val twoDigits = when (toDigits.length) {
            0 -> ""
            1 -> toDigits + toDigits
            2 -> toDigits
            else -> toDigits[0] + "" + toDigits[toDigits.length - 1]
        }
        println(twoDigits)
        //assert(twoDigits == "29")
        //assert(twoDigits == "29")
    }
}

private fun String.toDigits(): String {
    var result = ""
    for (i in this.indices) {
        val c = this[i]
        if (c.isDigit()) {
            result += c
        } else {
            if (length - i >= 3) {
                val substring = substring(i, i + 3)
                when (substring) {
                    "one" -> {
                        result += "1"
                    }
                    "two" -> {
                        result += "2"
                    }
                    "six" -> {
                        result += "6"
                    }
                }
            }
            if (length - i >= 4) {
                val substring = substring(i, i + 4)
                when (substring) {
                    "zero" -> {
                        result += "0"
                    }
                    "four" -> {
                        result += "4"
                    }
                    "five" -> {
                        result += "5"
                    }
                    "nine" -> {
                        result += "9"
                    }
                }
            }
            if (length - i >= 5) {
                val substring = substring(i, i + 5)
                when (substring) {
                    "three" -> {
                        result += "3"
                    }
                    "seven" -> {
                        result += "7"
                    }
                    "eight" -> {
                        result += "8"
                    }
                }
            }
        }
    }
    return result
}
