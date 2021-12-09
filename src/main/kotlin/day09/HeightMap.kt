package day09

object HeightMap {

    fun countLowPoints(values: List<List<Int>>): MutableList<Int> {
        val lowPoints = mutableListOf<Int>()
        for (i in values.indices) {
            for (j in values[i].indices) {
                val isLowPoint =
                    (i == 0 || values[i][j] < values[i - 1][j]) &&
                            (i == values.size - 1 || values[i][j] < values[i + 1][j]) &&
                            (j == 0 || values[i][j] < values[i][j - 1]) &&
                            (j == values[i].size - 1 || values[i][j] < values[i][j + 1])
                if (isLowPoint) {
                    lowPoints.add(values[i][j])
                }
            }
        }
        return lowPoints
    }


    fun countBasins(values: List<List<Int>>): Int {
        val basins = mutableListOf<MutableList<Pair<Pair<Int, Int>, Int>>>()
        for (i in values.indices) {
            for (j in values[i].indices) {
                val isLowPoint =
                    (i == 0 || values[i][j] < values[i - 1][j]) &&
                            (i == values.size - 1 || values[i][j] < values[i + 1][j]) &&
                            (j == 0 || values[i][j] < values[i][j - 1]) &&
                            (j == values[i].size - 1 || values[i][j] < values[i][j + 1])
                if (isLowPoint) {
                    val basinsIJ = getBasin(i, j, values)
                    basins.add(basinsIJ.distinctBy { it.first }.toMutableList())
                }
            }
        }
        val sorted = basins.map { it.size }.sorted()
        return sorted[sorted.size - 1] * sorted[sorted.size - 2] * sorted[sorted.size - 3]
    }

    private fun getBasin(i: Int, j: Int, values: List<List<Int>>): MutableList<Pair<Pair<Int, Int>, Int>> {
        var basinCount = mutableListOf(Pair(Pair(i, j), values[i][j]))
        if (i != 0 && values[i - 1][j] > values[i][j] && values[i - 1][j] != 9) {
            basinCount.addAll(getBasin(i - 1, j, values))
        }
        if (i != values.size - 1 && values[i + 1][j] > values[i][j] && values[i + 1][j] != 9) {
            basinCount.addAll(getBasin(i + 1, j, values))
        }
        if (j != 0 && values[i][j - 1] > values[i][j] && values[i][j - 1] != 9) {
            basinCount.addAll(getBasin(i, j - 1, values))
        }
        if (j != values[i].size - 1 && values[i][j + 1] > values[i][j] && values[i][j + 1] != 9) {
            basinCount.addAll(getBasin(i, j + 1, values))
        }
        return basinCount
    }
}