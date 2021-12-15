package day15

object Chitons {

    fun getLowestRiskPath2(toSolve: List<List<Int>>): Long {
        val graph = toSolve.toGraph2()
        val start = Vertex(0, 0)
        val end = Vertex(5 * toSolve.size - 1, 5 * toSolve[toSolve.size - 1].size - 1)
        return getShortestPathDijkstra(start, end, graph)
    }


    fun getLowestRiskPath(toSolve: List<List<Int>>): Long {
        val graph = toSolve.toGraph()
        val start = Vertex(0, 0)
        val end = Vertex(toSolve.size - 1, toSolve[toSolve.size - 1].size - 1)
        return getShortestPathDijkstra(start, end, graph)
    }

    private fun getShortestPathDijkstra(
        start: Vertex,
        end: Vertex,
        graph: MutableMap<String, MutableList<Edge>>
    ): Long {

        var q = mutableSetOf<String>()

        val vertices = graph.keys

        val dist = mutableMapOf<String, Long>()

        vertices.map {
            dist[it] = Long.MAX_VALUE
            q.add(it)
        }
        dist[start.name] = 0

        while (q.isNotEmpty()) {
            val u = q.minByOrNull { dist[it]!! }!!
            q.remove(u)
            val unvisitedNeighbours = getNeighbours(u, graph).filter { q.contains(it.name) }
            for (v in unvisitedNeighbours) {
                val alt = dist[u]!! + graph[u]!!.first { it.to == v }.value.toLong()
                if (alt < dist[v.name]!! && alt > 0) {
                    dist[v.name] = alt
                }
            }
        }
        return dist[end.name]!!
    }

    private fun getNeighbours(u: String, graph: MutableMap<String, MutableList<Edge>>): List<Vertex> {
        return graph[u]!!.map { it.to }
    }
}

private fun List<List<Int>>.toGraph(): MutableMap<String, MutableList<Edge>> {
    val graph = mutableMapOf<String, MutableList<Edge>>()
    for (i in this.indices) {
        for (j in this[i].indices) {
            if (graph[Vertex(i, j).name] == null) {
                graph[Vertex(i, j).name] = mutableListOf()
            }
            if (i < this.size - 1) {
                graph[Vertex(i, j).name]!!.add(Edge(Vertex(i, j), Vertex(i + 1, j), this[i + 1][j]))
            }
            if (j < this[i].size - 1) {
                graph[Vertex(i, j).name]!!.add(Edge(Vertex(i, j), Vertex(i, j + 1), this[i][j + 1]))
            }
        }
    }
    return graph
}

private fun List<List<Int>>.toGraph2(): MutableMap<String, MutableList<Edge>> {
    val graph = mutableMapOf<String, MutableList<Edge>>()
    val horSize = 5 * this.size
    val verSize = 5 * this[0].size
    for (i in 0 until verSize) {
        for (j in 0 until horSize) {
            val idx = i % this.size
            val idy = j % this[idx].size
            val idxPlusOne = (i + 1) % this.size
            val idyPlusOne = (j + 1) % this[idx].size
            val idxMinusOne = (i - 1) % this.size
            val idyMinusOne = (j - 1) % this[idx].size
            if (graph[Vertex(i, j).name] == null) {
                graph[Vertex(i, j).name] = mutableListOf()
            }
            if (i < verSize - 1) {
                var cost = this[idxPlusOne][idy] + (i + 1) / this.size + j / this[idx].size
                if (cost > 9) cost -= 9
                graph[Vertex(i, j).name]!!.add((Edge(Vertex(i, j), Vertex(i + 1, j), cost)))
            }
            if (j < horSize - 1) {
                var cost = this[idx][idyPlusOne] + i / this.size + (j + 1) / this[idx].size
                if (cost > 9) cost -= 9
                graph[Vertex(i, j).name]!!.add(Edge(Vertex(i, j), Vertex(i, j + 1), cost))
            }
            if (i > 0) {
                var cost = this[idxMinusOne][idy] + (i - 1) / this.size + j / this[idx].size
                if (cost > 9) cost -= 9
                graph[Vertex(i, j).name]!!.add(Edge(Vertex(i, j), Vertex(i - 1, j), cost))
            }
            if (j > 0) {
                var cost = this[idx][idyMinusOne] + i / this.size + (j - 1) / this[idx].size
                if (cost > 9) cost -= 9
                graph[Vertex(i, j).name]!!.add(Edge(Vertex(i, j), Vertex(i, j - 1), cost))
            }
        }
    }


    return graph
}

data class Edge(val from: Vertex, val to: Vertex, val value: Int) {

}

data class Vertex(val name: String) {
    constructor(i: Int, j: Int) : this("$i,$j")

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Vertex

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }

}
