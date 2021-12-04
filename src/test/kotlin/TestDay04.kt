import day04.BingoGame.toDay04Bingo
import org.junit.Assert
import org.junit.Test
import util.Util.getInstance

class TestDay04 {

    @Test
    fun `day 04 part 1 sample`() {
        val bingoGame = getInstance("day04_sample")
            .toDay04Bingo()
        val result = bingoGame.runBingoGame()
        Assert.assertEquals(24, result.second)
        Assert.assertEquals(188, result.first)
    }

    @Test
    fun `day 04 part 1`() {
        val bingoGame = getInstance("day04")
            .toDay04Bingo()
        val result = bingoGame.runBingoGame()
        print(result.first * result.second)
    }

    @Test
    fun `day 04 part 2 sample`() {
        val bingoGame = getInstance("day04_sample")
            .toDay04Bingo()
        val result = bingoGame.runBingoGame2()
        Assert.assertEquals(13, result.second)
        Assert.assertEquals(148, result.first)
    }

    @Test
    fun `day 04 part 2`() {
        val bingoGame = getInstance("day04")
            .toDay04Bingo()
        val result = bingoGame.runBingoGame2()
        print(result.first * result.second)
    }
}

