import day05.CoordinateMapper.checkMatrix
import day05.CoordinateMapper.toLineCoordinate
import org.junit.Assert
import org.junit.Test
import util.Util.getInstance

class TestDay05 {
    @Test
    fun `day 01 part 1 sample`() {
        val lines = getInstance("day05_sample")
            .map { it.toLineCoordinate() }
        val sum = checkMatrix(lines)
        Assert.assertEquals(5, sum)
    }

    @Test
    fun `day 01 part 1`() {
        val lines = getInstance("day05")
            .map { it.toLineCoordinate() }
        val sum = checkMatrix(lines)
        print(sum)
    }

    @Test
    fun `day 01 part 2 sample`() {
        val lines = getInstance("day05_sample")
            .map { it.toLineCoordinate() }
        val sum = checkMatrix(lines, useDiagonal = true)
        Assert.assertEquals(12, sum)
    }

    @Test
    fun `day 01 part 2`() {
        val lines = getInstance("day05")
            .map { it.toLineCoordinate() }
        val sum = checkMatrix(lines, useDiagonal = true)
        print(sum)
    }
}


