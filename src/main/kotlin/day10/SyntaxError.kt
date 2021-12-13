package day10

import java.util.*

object SyntaxError {

    private val score = mapOf(')' to 3, ']' to 57, '}' to 1197, '>' to 25137)
    private val score2 = mapOf(')' to 1, ']' to 2, '}' to 3, '>' to 4)

    private val openingsToClosing = mapOf('(' to ')', '[' to ']', '{' to '}', '<' to '>')

    private fun isOpening(c: Char) = openingsToClosing.keys.contains(c)

    fun computeScore(values: List<String>): Int {
        return values.map {
            computeScoreOfLine(it)
        }
            .reduce { a, b -> a + b }
    }

    private fun computeScoreOfLine(line: String): Int {
        val openings = ArrayDeque<Char>()
        for (i in line.indices) {
            val currChar = line[i]
            if (isOpening(currChar)) {
                openings.push(currChar)
            } else {
                val opening = openings.pop()
                if (currChar != openingsToClosing[opening]) {
                    return score[currChar]!!
                }
            }
        }
        return 0
    }

    fun computeScore2(values: List<String>): Long {
        val toScores = values.map {
            computeScoreOfLine2(it)
        }
            .filter { it != 0L }
            .sorted()
        return toScores[toScores.size / 2]
    }


    private fun computeScoreOfLine2(line: String): Long {
        val openings = ArrayDeque<Char>()
        for (currChar in line) {
            if (isOpening(currChar)) {
                openings.push(currChar)
            } else {
                val opening = openings.pop()
                if (currChar != openingsToClosing[opening]) {
                    return 0
                }
            }
        }
        return scoreAutocomplete(openings)
    }

    private fun scoreAutocomplete(openings: ArrayDeque<Char>): Long {
        var score: Long = 0
        for (i in openings.indices) {
            val currClosing = openingsToClosing[openings.pop()]
            score = score * 5 + score2[currClosing]!!
        }
        return score
    }
}