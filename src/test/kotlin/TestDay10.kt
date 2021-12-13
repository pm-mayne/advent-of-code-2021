import day10.SyntaxError
import org.junit.Test
import util.Util.getInstance
import kotlin.test.assertEquals

class TestDay10 {

    @Test
    fun `day 10 part 1 sample`() {
        val instance = getInstance("day10_sample")
        val score = SyntaxError.computeScore(instance)
        assertEquals(26397, score)
    }

    @Test
    fun `day 10 part 1`() {
        val instance = getInstance("day10")
        val score = SyntaxError.computeScore(instance)
        print(score)
    }

    @Test
    fun `day 10 part 2 sample`() {
        val instance = getInstance("day10_sample")
        val score = SyntaxError.computeScore2(instance)
        assertEquals(288957, score)
    }

    @Test
    fun `day 10 part 2`() {
        val instance = getInstance("day10")
        val score = SyntaxError.computeScore2(instance)
        print(score)
    }
}