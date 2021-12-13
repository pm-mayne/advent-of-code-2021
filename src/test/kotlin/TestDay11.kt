import day11.Octopus
import day11.OctopusBehaviour.countSteps
import day11.OctopusBehaviour.initNeighbour
import day11.OctopusBehaviour.runSteps
import org.junit.Test
import util.Util.getInstance
import kotlin.test.assertEquals

class TestDay11 {

    @Test
    fun `day 11 part 1 sample`() {
        val octopuses = getInstance("day11_sample").map { it.map { c -> c.toOctopus()}}
        initNeighbour(octopuses)
        val flashes = runSteps(100, octopuses)
        assertEquals(1656, flashes)
    }

    @Test
    fun `day 11 part 1 sample small`() {
        val octopuses = getInstance("day11_sample").map { it.map { c -> c.toOctopus()}}
        initNeighbour(octopuses)
        val flashes = runSteps(2, octopuses)
        assertEquals(35, flashes)
    }

    @Test
    fun `day 11 part 1 sample very small`() {
        val octopuses = getInstance("day11_sample_small").map { it.map { c -> c.toOctopus()}}
        initNeighbour(octopuses)
        val flashes = runSteps(2, octopuses)
        assertEquals(9, flashes)
    }

    @Test
    fun `day 11 part 1`() {
        val octopuses = getInstance("day11").map { it.map { c -> c.toOctopus()}}
        initNeighbour(octopuses)
        val flashes = runSteps(100, octopuses)
        print(flashes)
    }

    @Test
    fun `day 11 part 2 sample`() {
        val octopuses = getInstance("day11_sample").map { it.map { c -> c.toOctopus()}}
        initNeighbour(octopuses)
        val countSteps = countSteps(octopuses)
        assertEquals(195, countSteps)
    }

    @Test
    fun `day 11 part 2`() {
        val octopuses = getInstance("day11").map { it.map { c -> c.toOctopus()}}
        initNeighbour(octopuses)
        val countSteps = countSteps(octopuses)
        print(countSteps)
    }
}

private fun Char.toOctopus(): Octopus {
    return Octopus((this + "").toInt())
}
