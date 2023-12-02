package aoc2023.day02

data class Game(val id: Int, val cubesAndQuantityMap: Map<String, Int>) {

    fun accepts(acceptance: Map<String, Int>): Boolean {
        return acceptance
            .all { !this.cubesAndQuantityMap.containsKey(it.key) || this.cubesAndQuantityMap[it.key]!! <= it.value }
    }

    fun power(): Int {
        return this.cubesAndQuantityMap.values
            .reduce { acc, b -> acc * b }
    }


}
