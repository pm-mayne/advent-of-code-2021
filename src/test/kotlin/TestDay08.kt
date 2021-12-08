import day08.DigitSegmentScreen.countEasyDigits
import day08.DigitSegmentScreen.decodeScreens
import org.junit.Test
import util.Util.getInstance
import kotlin.test.assertEquals

class TestDay08 {

    @Test
    fun `day08 part 1 sample`() {
        val count = countEasyDigits(getInstance("day08_sample"))
        assertEquals(0, count)
    }

    @Test
    fun `day08 part 1 sample large`() {
        val count = countEasyDigits(getInstance("day08_sample_large"))
        assertEquals(26, count)
    }

    @Test
    fun `day08 part 1`() {
        val count = countEasyDigits(getInstance("day08"))
        print(count)
    }

    @Test
    fun `day08 part 2 sample`() {
        val count = decodeScreens(getInstance("day08_sample"))
        assertEquals(5353, count)
    }

    @Test
    fun `day08 part 2 sample large`() {
        val count = decodeScreens(getInstance("day08_sample_large"))
        assertEquals(61229, count)
    }

    @Test
    fun `day08 part 2`() {
        val count = decodeScreens(getInstance("day08"))
        print(count)
    }
}