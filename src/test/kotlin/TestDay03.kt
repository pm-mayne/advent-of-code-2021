import day03.BinaryDiagnostic
import org.junit.Assert
import org.junit.Test
import util.Util.getInstance

class TestDay03 {
    @Test
    fun `day 03 part 1 test`() {
        val instance = getInstance("day03_sample")
        val gammaAndEpsilon = BinaryDiagnostic.getGammaAndEpsilonRate(instance)
        Assert.assertEquals(22, gammaAndEpsilon.gamma)
        Assert.assertEquals(9, gammaAndEpsilon.epsilon)
        Assert.assertEquals(198, gammaAndEpsilon.gamma * gammaAndEpsilon.epsilon)
    }

    @Test
    fun `day 03 part 1`() {
        val instance = getInstance("day03")
        val gammaAndEpsilon = BinaryDiagnostic.getGammaAndEpsilonRate(instance)
        print(gammaAndEpsilon.gamma * gammaAndEpsilon.epsilon)
    }

    @Test
    fun `day 03 part 2 test`() {
        val instance = getInstance("day03_sample")
        val oxygenAndCO2Rate = BinaryDiagnostic.getOxygenAndCO2Rate(instance)
        Assert.assertEquals(23, oxygenAndCO2Rate.oxygen)
        Assert.assertEquals(10, oxygenAndCO2Rate.co2)
        Assert.assertEquals(230, oxygenAndCO2Rate.oxygen * oxygenAndCO2Rate.co2)
    }

    @Test
    fun `day 03 part 2`() {
        val instance = getInstance("day03")
        val gammaAndEpsilon = BinaryDiagnostic.getOxygenAndCO2Rate(instance)
        print(gammaAndEpsilon.oxygen * gammaAndEpsilon.co2)
    }
}