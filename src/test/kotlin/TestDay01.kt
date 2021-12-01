import day01.IncreaseCounter.countIncreases
import day01.IncreaseCounter.countIncreasesOnSlidingWindow
import org.junit.Test
import util.Util.getInstance

class TestDay01 {

    @Test
    fun `day 01 part 1`() {
        val instance = getInstance("day01")
            .filter { it.isNotEmpty() }
            .map { it.toInt() }

        println(countIncreases(instance))
    }

    @Test
    fun `day 01 part 2`() {
        val instance = getInstance("day01")
            .filter { it.isNotEmpty() }
            .map { it.toInt() }

        println(countIncreasesOnSlidingWindow(instance, 3))
    }
}