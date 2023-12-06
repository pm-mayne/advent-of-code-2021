package aoc2023.day04

data class Card(val id: Int, val winningNumbers: List<Int>, val myNumbers: List<Int>) {

    public fun getScore(): Int {
        val nbOfWins = myNumbers.intersect(winningNumbers).size
        if (nbOfWins > 0) {
            return Math.pow(2.0, nbOfWins.toDouble() - 1).toInt()
        }
        return 0
    }

    fun toSubsequentCards(cardMap: Map<Int, Card>): List<Card> {
        val result = mutableListOf(this)
        val nbOfWins = myNumbers.intersect(winningNumbers).size
        for (i in 1..nbOfWins) {
            if (id + i in cardMap.keys) {
                result.addAll(cardMap[id + i]!!.toSubsequentCards(cardMap))
            }
        }
        return result
    }

}
