import day02.SubmarinePosition.getPosition
import day02.SubmarinePosition.getPosition2
import org.junit.Assert
import org.junit.Test
import util.Util.getInstance

class TestDay02 {

    @Test
    fun `day 02 part 1 test`() {
        val instance = getInstance("day02_sample")
        val position = getPosition(instance)
        Assert.assertEquals(10, position.depth)
        Assert.assertEquals(15, position.horizontal)
    }

    @Test
    fun `day 02 part 1`() {
        val instance = getInstance("day02")
        val position = getPosition(instance)
        print(position.depth * position.horizontal)
    }

    @Test
    fun `day 02 part 2 test`() {
        val instance = getInstance("day02_sample")
        val position = getPosition2(instance)
        Assert.assertEquals(60, position.depth)
        Assert.assertEquals(15, position.horizontal)
    }

    @Test
    fun `day 02 part 2`() {
        val instance = getInstance("day02")
        val position = getPosition2(instance)
        print(position.depth * position.horizontal)
    }
}