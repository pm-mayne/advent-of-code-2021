package day04

object BingoGame {


    fun List<String>.toDay04Bingo(): Bingo {
        val numbers = this[0].split(",").map { it.toInt() }

        val bingoGrids = mutableListOf(BingoGrid())
        var k = 0
        for (i in 2 until this.size) {
            if (this[i].isEmpty()) {
                bingoGrids.add(BingoGrid())
                k = 0
            } else {
                bingoGrids.last().values.add(mutableListOf())
                val line = this[i].split(" ").filter { it.isNotEmpty() }.map { it.trim() }.map { it.toInt() }
                for (j in line.indices) {
                    val value = line[j]
                    bingoGrids.last().values[k].add(value)
                }
                k++
            }
        }

        return Bingo(numbers, bingoGrids.map { it.toBingoLines() }.toMutableList())
    }
}

data class Bingo(val numbers: List<Int>, var gridLines: MutableList<MutableList<MutableList<Int>>>) {

    fun runBingoGame(): Pair<Int, Int> {

        var i = 0
        while (i < numbers.size) {
            pick(numbers[i])
            if (isOver()) {
                val sum = gridLines.filter { lines ->
                    lines.any { line -> line.isEmpty() }
                }
                    .flatten()
                    .flatten()
                    .reduce { a, b -> a + b } / 2
                return Pair(sum, numbers[i])
            }
            i++
        }
        return Pair(
            0, 0
        )

    }

    private fun pick(i: Int) {
        val newLines = gridLines.map { lines ->
            lines.map { line ->
                line.remove(i)
                line
            }.toMutableList()
        }.toMutableList()
        gridLines = newLines
    }

    private fun isOver(): Boolean {
        return gridLines.flatten().any { it.isEmpty() }
    }

    fun runBingoGame2(): Pair<Int, Int> {

        var i = 0
        while (i < numbers.size) {
            pickAndEliminate(numbers[i])
            if (isOver()) {
                val sum = gridLines.filter { lines ->
                    lines.any { line -> line.isEmpty() }
                }
                    .flatten()
                    .flatten()
                    .reduce { a, b -> a + b } / 2
                return Pair(sum, numbers[i])
            }
            i++
        }
        return Pair(
            0, 0
        )

    }

    private fun pickAndEliminate(i: Int) {
        pick(i)
        val newLines = gridLines.filter { grid ->
            grid.none { line -> line.isEmpty() }
        }.toMutableList()
        if(newLines.isNotEmpty()){
            gridLines = newLines
        }
    }
}


data class BingoGrid(val values: MutableList<MutableList<Int>> = mutableListOf()) {

    fun toBingoLines(): MutableList<MutableList<Int>> {
        val columns = mutableListOf<MutableList<Int>>()
        val rows = mutableListOf<MutableList<Int>>()
        for (i in values.indices) {
            rows.add(mutableListOf())
            for (j in values[i].indices) {
                if (i == 0) {
                    columns.add(mutableListOf())
                }
                rows[i].add(values[i][j])
                columns[j].add(values[i][j])
            }
        }
        return (columns + rows) as MutableList<MutableList<Int>>
    }
}
