package day14

object PatternTransform {

    fun transformN(n: Int, chain: String, instructions: Map<String, String>): String {
        var chainAfterN = chain
        for (i in 0 until n) {
            println(chainAfterN)
            var changingChain = "" + chainAfterN[0]
            for (j in 0.until(chainAfterN.length - 1)) {
                val pattern = "" + chainAfterN[j] + chainAfterN[j + 1]
                val insertion = instructions[pattern]
                if (insertion != null) {
                    changingChain += insertion + chainAfterN[j + 1]
                } else {
                    changingChain += chainAfterN[j + 1]
                }
            }
            chainAfterN = changingChain
        }
        return chainAfterN
    }

    fun createGraph(chain: String, instructions: Map<String, String>): List<Pair<String, String>> {
        val graph = mutableListOf<Pair<String, String>>()
        for (i in 0.until(chain.length - 1)) {
            graph.add(Pair("0", "" + chain[i] + chain[i + 1]))
        }
        for (instruction in instructions) {
            graph.add(Pair(instruction.key, instruction.key[0] + instruction.value))
            graph.add(Pair(instruction.key, instruction.value + instruction.key[1]))
        }
        return graph

    }

    fun searchGraphAndCount(
        graph: List<Pair<String, String>>,
        n: Int,
        chain: String
    ): Map<String, Long> {
        val count = mutableMapOf<String, Long>()
        for (i in 0.until(chain.length - 1)) {
            val countInit = count.getOrElse("" + chain[i] + chain[i + 1]) { 0 }
            count["" + chain[i] + chain[i + 1]] = countInit + 1
        }

        for (i in 0 until n) {
            advance(graph, count)
        }

        val finalCount = mutableMapOf<String, Long>()
        count.map {
            if (it.key.length == 1) {
                listOf(it.key to it.value)
            } else {
                listOf(it.key[0] + "" to it.value, it.key[1] + "" to it.value)
            }
        }
            .flatten()
            .map {
                val countOfLetter = finalCount.getOrElse(it.first) { 0 }
                finalCount[it.first] = countOfLetter + it.second
                it
            }

        return finalCount


    }

    private fun advance(
        graph: List<Pair<String, String>>,
        count: MutableMap<String, Long>
    ) {
        val newCounts = mutableMapOf<String, Long>()
        for (c in count.filter { it.value != 0L }) {
            val oldVal = newCounts.getOrElse(c.key) { 0 }
            newCounts[c.key] = oldVal - c.value
            val children = graph.filter { it.first == c.key }
            children.map {
                val oldValForChild = newCounts.getOrElse(it.second) { 0 }
                newCounts[it.second] = c.value + oldValForChild
            }
        }
        for (c in newCounts) {
            val oldVal = count.getOrElse(c.key) { 0 }
            count[c.key] = oldVal + c.value
        }
    }
}