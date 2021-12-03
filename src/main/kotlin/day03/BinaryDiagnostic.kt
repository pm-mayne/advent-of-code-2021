package day03

object BinaryDiagnostic {
    fun getGammaAndEpsilonRate(instance: List<String>): GammaAndEpsilon {

        val transpose: List<String> = instance.transpose()
        val gamAndEpsStr = transpose
            .map { it.toMostCommonAndLeastCommon() }
            .reduce { a, b -> Pair(a.first + b.first, a.second + b.second) }

        return GammaAndEpsilon(gamAndEpsStr.first, gamAndEpsStr.second)
    }

    fun getOxygenAndCO2Rate(instance: List<String>): OxygenAndCO2 {
        val transposed = instance.transpose()
        var filteringForOxygen = instance
        for (i in transposed.indices) {
            filteringForOxygen =
                filteringForOxygen.filter { "" + it[i] == filteringForOxygen.transpose()[i].toMostCommonAndLeastCommon().first }
            if (filteringForOxygen.size == 1) {
                break
            }
        }
        var filteringForCO2 = instance
        for (i in transposed.indices) {
            filteringForCO2 =
                filteringForCO2.filter { "" + it[i] == filteringForCO2.transpose()[i].toMostCommonAndLeastCommon().second }
            if (filteringForCO2.size == 1) {
                break
            }
        }
        return OxygenAndCO2(filteringForOxygen[0], filteringForCO2[0])
    }

}

private fun List<String>.transpose(): List<String> {
    val tmp = mutableListOf<String>()
    for (i in this[0].indices) {
        tmp.add(this
            .map { "" + it[i] }
            .reduce { acc, e -> acc + e }
        )
    }
    return tmp
}

private fun String.toMostCommonAndLeastCommon(): Pair<String, String> {
    val countZero = this.filter { it == '0' }.count()
    val countOne = this.length - countZero
    if (countOne < countZero) {
        return Pair("0", "1")
    }
    return Pair("1", "0")
}

data class GammaAndEpsilon(val gamma: Int, val epsilon: Int) {
    constructor(gammaStr: String, epsilonStr: String) : this(gammaStr.toInt(2), epsilonStr.toInt(2))
}

data class OxygenAndCO2(val oxygen: Int, val co2: Int) {
    constructor(oxygenStr: String, co2Str: String) : this(oxygenStr.toInt(2), co2Str.toInt(2))
}