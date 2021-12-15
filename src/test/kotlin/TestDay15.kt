import day15.Chitons.getLowestRiskPath
import day15.Chitons.getLowestRiskPath2
import org.junit.Test
import util.Util.getInstance
import util.Util.toMatrix
import kotlin.test.assertEquals

class TestDay15 {

    @Test
    fun `day 15 part 1 sample`() {
        val instance = getInstance("day15_sample").toMatrix()
        val result = getLowestRiskPath(instance)
        assertEquals(40, result)
    }

    @Test
    fun `day 15 part 1`() {
        val instance = getInstance("day15").toMatrix()
        val result = getLowestRiskPath(instance)
        print(result)
    }

    @Test
    fun `day 15 part 2 sample`() {
        val instance = getInstance("day15_sample").toMatrix()
        val result = getLowestRiskPath2(instance)
        assertEquals(315, result)
    }

    @Test
    fun `day 15 part 2`() {
        val instance = getInstance("day15").toMatrix()
        val result = getLowestRiskPath2(instance)
        println("Result: $result")
    }
}