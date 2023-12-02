import BITS.sumOfVersions
import BITS.sumOfVersionsOfBinaryString
import org.junit.Test
import util.Util.getInstance
import kotlin.test.assertEquals

class TestDay16 {

    @Test
    fun `day 16 part 1 binary`() {
        val sumOfVersionsOfBinaryString =
            sumOfVersionsOfBinaryString("00111000000000000110111101000101001010010001001000000000")
    }

    @Test
    fun `day 16 part 1 sample`() {
        var sample = "8A004A801A8002F478"
        val sumOfVersions1 = sumOfVersions(sample)
        assertEquals(16, sumOfVersions1)
        var sample2 = "620080001611562C8802118E34"
        val sumOfVersions2 = sumOfVersions(sample2)
        assertEquals(12, sumOfVersions2)
        var sample3 = "C0015000016115A2E0802F182340"
        val sumOfVersions3 = sumOfVersions(sample3)
        assertEquals(23, sumOfVersions3)
        var sample4 = "C0015000016115A2E0802F182340"
        val sumOfVersions4 = sumOfVersions(sample4)
        assertEquals(31, sumOfVersions4)
    }

    @Test
    fun `day 16 part 1`() {
        //var sample = "8A004A801A8002F478"
        //val sumOfVersions1 = sumOfVersions(sample)
        //assertEquals(16, sumOfVersions1)
        var instance = getInstance("Day16")[0]
        val sumOfVersions = sumOfVersions(instance)
        print(sumOfVersions)
    }


    @Test
    fun `day 16 part 2`() {
        //var sample = "8A004A801A8002F478"
        //val sumOfVersions1 = sumOfVersions(sample)
        //assertEquals(16, sumOfVersions1)
        var instance = getInstance("Day16")[0]
        val sumOfVersions = sumOfVersions(instance)
        print(sumOfVersions)
    }
}