import day12.Cave
import day12.CaveSearch.countPaths
import day12.CaveSearch.countPaths2
import day12.Edge
import org.junit.Test
import util.Util.getInstance
import kotlin.test.assertEquals

class TestDay12 {

    @Test
    fun `day 12 part 1 sample 1`() {
        val graph = getInstance("day12_sample_1").map { it.toEdge() }
        val countPaths = countPaths(graph)
        assertEquals(19, countPaths)
    }

    @Test
    fun `day 12 part 1`() {
        val graph = getInstance("day12").map { it.toEdge() }
        val countPaths = countPaths(graph)
        print(countPaths)
    }

    @Test
    fun `day 12 part 2 sample 1`() {
        val graph = getInstance("day12_sample_1").map { it.toEdge() }
        val countPaths = countPaths2(graph)
        assertEquals(103, countPaths)
    }

    @Test
    fun `day 12 part 2`() {
        val graph = getInstance("day12").map { it.toEdge() }
        val countPaths = countPaths2(graph)
        print(countPaths)
    }

}

private fun String.toEdge(): Edge {
    return Edge(Cave(this.split("-")[0]), Cave(this.split("-")[1]))
}
