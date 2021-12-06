import day06.LanternFish
import day06.LanternFishLifecycle
import org.junit.Test
import util.Util.getInstance
import kotlin.test.assertEquals

class TestDay06 {
    @Test
    fun `test day 06 part 1`() {
        val school = getInstance("day06_sample")[0].split(",").map { it.toLanternFish() }.toMutableList()
        LanternFishLifecycle.cycleNDays(80, school)
        assertEquals(5934, school.size)
    }

    @Test
    fun `day 06 part 1`() {
        val school = getInstance("day06")[0].split(",").map { it.toLanternFish() }.toMutableList()
        LanternFishLifecycle.cycleNDays(80, school)
        print(school.size)
    }

    @Test
    fun `test day 06 part 2`() {
        val school = getInstance("day06_sample")[0].split(",").map { it.toLanternFish() }.toMutableList()
        val total = LanternFishLifecycle.computeLifeCycleSmart(256, school)
        assertEquals(26984457539, total)
    }

    @Test
    fun `day 06 part 2`() {
        val school = getInstance("day06")[0].split(",").map { it.toLanternFish() }.toMutableList()
        val count = LanternFishLifecycle.computeLifeCycleSmart(256, school)
        print(count)
    }
}

private fun String.toLanternFish(): LanternFish {
    return LanternFish(this.toInt())
}
