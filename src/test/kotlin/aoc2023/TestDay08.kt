package aoc2023

import org.junit.Test
import util.Util

class TestDay08 {

    private val example = listOf(
        "LR",
        "",
        "11A = (11B, XXX)",
        "11B = (XXX, 11Z)",
        "11Z = (11B, XXX)",
        "22A = (22B, XXX)",
        "22B = (22C, 22C)",
        "22C = (22Z, 22Z)",
        "22Z = (22B, 22B)",
    )

    @Test
    fun `day 08 part 1`() {
        val graph = Util.getInstance("aoc2023/day08").toGraph()
        //val graph = example.toGraph()

        var current = graph.vertices["AAA"]
        val arrival = graph.vertices["ZZZ"]
        var rOrLIndex = 0

        var steps = 0

        while (current != arrival) {
            steps++
            current = graph.vertices[current!!.getBy(graph.rlPattern[rOrLIndex])]
            rOrLIndex++
            if (rOrLIndex >= graph.rlPattern.length) {
                rOrLIndex = 0
            }
        }

        print("Staps: $steps")
    }

    @Test
    fun `day 08 part 2`() {
        val graph = Util.getInstance("aoc2023/day08").toGraph()
        //val graph = example.toGraph()

        var starts = graph.vertices.filter { it.key.last() == 'A' }.map { it.value }
        val arrivals = graph.vertices.filter { it.key.last() == 'Z' }.map { it.value }
        var rOrLIndex = 0



        val counts = mutableMapOf<String, Int>()

        for (start in starts) {
            var steps = 0
            var current = start
            while (!arrivals.contains(current)) {
                steps++
                current = graph.vertices[current!!.getBy(graph.rlPattern[rOrLIndex])]!!
                rOrLIndex++
                if (rOrLIndex >= graph.rlPattern.length) {
                    rOrLIndex = 0
                }
            }
            counts[start.id] = steps
        }
        print("Counts: $counts")

        // val ppcm = getPPCM(counts.values)
    }

    private fun getPPCM(values: MutableCollection<Int>): Any {
        TODO("Not yet implemented")
    }


}

private fun List<String>.toGraph(): Graph {
    val rlPattern = this[0]

    val vertices = this.takeLast(this.size - 2)
        .map { it.toVertex() }
        .associateBy { it.id }

    return Graph(rlPattern = rlPattern, vertices = vertices)
}

data class Graph(val rlPattern: String, val vertices: Map<String, Vertex>) {

}

private fun String.toVertex(): Vertex {
    val id = this.split(" = ")[0]
    val rlNeighbours = this.split(" = ")[1].split(", ")
    val nextByL = rlNeighbours[0].substring(startIndex = 1)
    val nextByR = rlNeighbours[1].take(rlNeighbours[1].length - 1)
    return Vertex(id = id, nextByR = nextByR, nextByL = nextByL)
}

data class Vertex(val id: String, val nextByR: String, val nextByL: String) {
    fun getBy(rOrL: Char): String {
        return when (rOrL) {
            'R' -> nextByR
            'L' -> nextByL
            else -> ""
        }
    }
}
