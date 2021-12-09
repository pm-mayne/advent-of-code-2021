import day09.HeightMap.countBasins
import day09.HeightMap.countLowPoints
import org.junit.Test
import util.Util.getInstance
import kotlin.test.assertEquals

class TestDay09 {
    @Test
    fun `test day 9 part 1`() {
        val instance = getInstance("day09_sample")
            .map { it.map { c -> (c + "").toInt() } }
        val result = countLowPoints(instance)
        val sum = result.reduce { a, b -> a + b }
        assertEquals(4, result.size)
        assertEquals(15, result.size + sum)
    }

    @Test
    fun `day 9 part 1`() {
        val instance = getInstance("day09")
            .map { it.map { c -> (c + "").toInt() } }
        val result = countLowPoints(instance)
        val sum = result.reduce { a, b -> a + b }
        print(sum + result.size)
    }

    @Test
    fun `test day 9 part 2`() {
        val instance = getInstance("day09_sample")
            .map { it.map { c -> (c + "").toInt() } }
        val basinCount = countBasins(instance)
        assertEquals(1134, basinCount)
    }

    @Test
    fun `day 9 part 2`() {
        val instance = getInstance("day09")
            .map { it.map { c -> (c + "").toInt() } }
        val basinCount = countBasins(instance)
        print(basinCount)
    }
}