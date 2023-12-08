package aoc2023

import org.junit.Test
import util.Util

class TestDay07 {

    private val example = listOf(
        "32T3K 765",
        "T55J5 684",
        "KK677 28",
        "KTJJT 220",
        "QQQJA 483",
    )

    @Test
    fun `day 07 part 1`() {
        val instance = Util.getInstance("aoc2023/day07")
        val result = instance
            .map { it.toHand() }
            .sortedBy { it.toFaceValue() }
            .mapIndexed { i, h ->
                println("${i + 1} : ${h.hand} ${h.bid} [${h.toFaceValue()}]")
                (i + 1) * h.bid
            }
            .sum()

        println("Result: $result")
    }

    @Test
    fun `day 07 part 2`() {
        val instance = Util.getInstance("aoc2023/day07")
        val result = instance
            .map { it.toHand() }
            .sortedBy { it.toFaceValueWithJokers() }
            .mapIndexed { i, h ->
                println("${i + 1} : ${h.hand} ${h.bid} [${h.toFaceValueWithJokers()}]")
                (i + 1) * h.bid
            }
            .sum()

        println("Result: $result")
    }

}

private fun String.toHand(): Hand {
    val split = this.split(" ")
    val hand = split[0]
    val bid = split[1].toInt()
    return Hand(hand = hand, bid = bid)
}

data class Hand(val hand: String, val bid: Int) {
    fun toFaceValue(): Int {
        return hand.getFigureScore() * 3_200_000 +
                hand[0].toFaceValue() * 160_000 +
                hand[1].toFaceValue() * 8_000 +
                hand[2].toFaceValue() * 400 +
                hand[3].toFaceValue() * 20 +
                hand[4].toFaceValue()
    }

    fun toFaceValueWithJokers(): Int {
        return hand.getFigureScoreWithJokers() * 3_200_000 +
                hand[0].toFaceValueWithJokers() * 160_000 +
                hand[1].toFaceValueWithJokers() * 8_000 +
                hand[2].toFaceValueWithJokers() * 400 +
                hand[3].toFaceValueWithJokers() * 20 +
                hand[4].toFaceValueWithJokers()
    }

}

private fun String.getFigureScore(): Int {
    when {
        this.isFiveOfKind() -> {
            return 7
        }
        this.isFourOfKind() -> {
            return 6
        }
        this.isFullHouse() -> {
            return 5
        }
        this.isThreeOfKind() -> {
            return 4
        }
        this.isThreeOfKind() -> {
            return 3
        }
        this.isTwoPairs() -> {
            return 2
        }
        this.isPair() -> {
            return 1
        }
        else -> {
            return 0
        }
    }
}

private fun String.getFigureScoreWithJokers(): Int {
    when {
        this.isFiveOfKindWithJokers() -> {
            return 7
        }
        this.isFourOfKindWithJokers() -> {
            return 6
        }
        this.isFullHouseWithJokers() -> {
            return 5
        }
        this.isThreeOfKindWithJokers() -> {
            return 4
        }
        this.isThreeOfKindWithJokers() -> {
            return 3
        }
        this.isTwoPairsWithJokers() -> {
            return 2
        }
        this.isPairWithJokers() -> {
            return 1
        }
        else -> {
            return 0
        }
    }
}

private fun String.isThreeOfKind(): Boolean {
    return this.groupBy { it }
        .any { it.value.size == 3 }
}

private fun String.isFullHouse(): Boolean {
    return this.groupBy { it }
        .any { it.value.size == 3 } &&
            this.groupBy { it }
                .any { it.value.size == 2 }
}

private fun String.isPair(): Boolean {
    return this.groupBy { it }
        .any { it.value.size == 2 }
}

private fun String.isTwoPairs(): Boolean {
    return this.groupBy { it }
        .filter { it.value.size == 2 }
        .count() == 2
}

private fun String.isFourOfKind(): Boolean {
    return this.groupBy { it }
        .any { it.value.size == 4 }
}

private fun String.isFiveOfKind(): Boolean {
    return this.all { it == this.first() }
}


private fun String.isThreeOfKindWithJokers(): Boolean {
    val nbJokers = this.getJokerNb()
    return this.withoutJokers().groupBy { it }
        .any { it.value.size + nbJokers == 3 }
}

private fun String.isFullHouseWithJokers(): Boolean {
    return this.isFullHouse()
            || (this.getJokerNb() == 1 && (this.isTwoPairs() || this.isThreeOfKind()))
            || (this.getJokerNb() == 2 && this.withoutJokers().isPair())
            || this.getJokerNb() >= 3
}

private fun String.isPairWithJokers(): Boolean {
    return this.groupBy { it }
        .any { it.value.size == 2 }
            || this.contains('J')
}

private fun String.isTwoPairsWithJokers(): Boolean {
    return (this.withoutJokers().isPair() && this.getJokerNb() >= 1)
            || this.isTwoPairs()
            || this.getJokerNb() >= 2
}

private fun String.isFourOfKindWithJokers(): Boolean {
    val nbJokers = this.getJokerNb()
    return this.withoutJokers().groupBy { it }
        .any { it.value.size + nbJokers == 4 }
}

private fun String.withoutJokers(): String {
    return this.filter { it != 'J' }
}

private fun String.getJokerNb(): Int {
    return this.filter { it == 'J' }.count()
}

private fun String.isFiveOfKindWithJokers(): Boolean {
    return this.filter { it != 'J' }.isFiveOfKind()
}


private fun Char.toFaceValue(): Int {
    return when (this) {
        'A' -> 13
        'K' -> 12
        'Q' -> 11
        'J' -> 10
        'T' -> 9
        '9' -> 8
        '8' -> 7
        '7' -> 6
        '6' -> 5
        '5' -> 4
        '4' -> 3
        '3' -> 2
        '2' -> 1
        else -> 0
    }
}

private fun Char.toFaceValueWithJokers(): Int {
    return when (this) {
        'A' -> 13
        'K' -> 12
        'Q' -> 11
        'T' -> 10
        '9' -> 9
        '8' -> 8
        '7' -> 7
        '6' -> 6
        '5' -> 5
        '4' -> 4
        '3' -> 3
        '2' -> 2
        'J' -> 1
        else -> 0
    }
}
