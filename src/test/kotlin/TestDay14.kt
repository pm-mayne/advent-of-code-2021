import day14.Instruction
import day14.PatternTransform.createGraph
import day14.PatternTransform.searchGraphAndCount
import day14.PatternTransform.transformN
import org.junit.Test
import util.Util.getInstance
import kotlin.test.assertEquals

class TestDay14 {

    @Test
    fun `day 14 part 1 sample`() {
        val chain = getInstance("day14_sample")[0]
        val instructions = getInstance("day14_sample")
            .takeLast(getInstance("day14_sample").size - 2)
            .map { it.toInstructions() }.associate { it.pattern to it.insertion }
        val chain2 = transformN(10, chain, instructions)
        val max = chain2.groupingBy { it }.eachCount().maxByOrNull { it.value }!!.value
        val min = chain2.groupingBy { it }.eachCount().minByOrNull { it.value }!!.value
        assertEquals(1588, max - min)
    }

    @Test
    fun `day 14 part 1`() {
        val chain = getInstance("day14")[0]
        val instructions = getInstance("day14")
            .takeLast(getInstance("day14").size - 2)
            .map { it.toInstructions() }.associate { it.pattern to it.insertion }
        val chain2 = transformN(10, chain, instructions)
        val max = chain2.groupingBy { it }.eachCount().maxByOrNull { it.value }!!.value
        val min = chain2.groupingBy { it }.eachCount().minByOrNull { it.value }!!.value
        print(max - min)
    }

    @Test
    fun `day 14 part 2 sample`() {
        val chain = getInstance("day14_sample")[0]
        val instructions = getInstance("day14_sample")
            .takeLast(getInstance("day14_sample").size - 2)
            .map { it.toInstructions() }.associate { it.pattern to it.insertion }
        val graph = createGraph(chain, instructions)
        val counter = searchGraphAndCount(graph, 40, chain)
        val max = counter.maxByOrNull { it.value }!!.value
        val min = counter.minByOrNull { it.value }!!.value
        assertEquals(2188189693529, (max - min)/2 + 1)
    }

    @Test
    fun `day 14 part 2`() {
        val chain = getInstance("day14")[0]
        val instructions = getInstance("day14")
            .takeLast(getInstance("day14").size - 2)
            .map { it.toInstructions() }.associate { it.pattern to it.insertion }
        val graph = createGraph(chain, instructions)
        val counter = searchGraphAndCount(graph, 40, chain)
        val max = counter.maxByOrNull { it.value }!!.value
        val min = counter.minByOrNull { it.value }!!.value
        print((max - min)/2 + 1)
    }

}

private fun String.toInstructions(): Instruction {
    return Instruction(this.split(" -> ")[0], this.split(" -> ")[1])
}
