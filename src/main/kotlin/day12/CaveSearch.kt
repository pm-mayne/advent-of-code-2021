package day12

object CaveSearch {


    fun countPaths(graph: List<Edge>): Int {
        val start = "start"
        val paths = mutableListOf(Cave("start"))
        val allPathsFrom = getAllPathsFrom(start, paths, graph)
        return allPathsFrom.size
    }

    private fun getAllPathsFrom(from: String, path: MutableList<Cave>, graph: List<Edge>): List<MutableList<Cave>> {
        if (from == "end") {
            return listOf(path)
        }
        return getDestinationsFrom(from, graph)
            .filter { it.isBig || !path.contains(it) }
            .flatMap { getAllPathsFrom(it.id, (path + it) as MutableList<Cave>, graph) }

    }

    fun countPaths2(graph: List<Edge>): Int {
        val start = "start"
        val paths = mutableListOf(Cave("start"))
        val allPathsFrom = getAllPathsFrom2(start, paths, graph)
        return allPathsFrom.size
    }

    private fun getAllPathsFrom2(from: String, path: MutableList<Cave>, graph: List<Edge>): List<MutableList<Cave>> {
        if (from == "end") {
            return listOf(path)
        }
        return getDestinationsFrom(from, graph)
            .filter { isCaveOk(it, path) }
            .flatMap { getAllPathsFrom2(it.id, (path + it) as MutableList<Cave>, graph) }

    }

    private fun isCaveOk(cave: Cave, path: MutableList<Cave>): Boolean {
        if (cave.isBig || !path.contains(cave)) {
            return true
        }
        if (path.contains(cave) && cave.id == "start") {
            return false
        }
        return path.filter { !it.isBig }.distinct().size == path.filter { !it.isBig }.size
    }

    private fun getDestinationsFrom(caveName: String, graph: List<Edge>): List<Cave> {
        return graph.filter { it.cave1.id == caveName || it.cave2.id == caveName }
            .map { it.getDestination(caveName) }
    }
}