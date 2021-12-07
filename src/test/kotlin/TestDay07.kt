import day07.CrabMoves.sumOfMoves
import day07.CrabMoves.sumOfMoves2
import org.junit.Test
import util.Util.getInstance
import kotlin.test.assertEquals

class TestDay07 {

    @Test
    fun `test day 07 part 1`() {
        val crabPositions = getInstance("day07_sample")[0].split(",").map { it.toInt() }
        val sumOfMoves = sumOfMoves(crabPositions)

        assertEquals(37, sumOfMoves)
    }

    @Test
    fun `day 07 part 1`() {
        val crabPositions = getInstance("day07")[0].split(",").map { it.toInt() }
        val sumOfMoves = sumOfMoves(crabPositions)

        print(sumOfMoves)
    }

    @Test
    fun `test day 07 part 2`() {
        val crabPositions = getInstance("day07_sample")[0].split(",").map { it.toInt() }
        val sumOfMoves = sumOfMoves2(crabPositions)

        assertEquals(168, sumOfMoves)
    }

    @Test
    fun `day 07 part 2`() {
        val crabPositions = getInstance("day07")[0].split(",").map { it.toInt() }
        val sumOfMoves = sumOfMoves2(crabPositions)

        print(sumOfMoves)
    }
}