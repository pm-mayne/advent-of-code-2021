import day13.FoldInstruction
import day13.Point
import day13.TransparentPaperFolding.foldTransparentPaper
import day13.TransparentPaperFolding.toFoldInstruction
import day13.TransparentPaperFolding.toPoint
import org.junit.Test
import util.Util.getInstance
import java.lang.Integer.max
import kotlin.test.assertEquals

class TestDay13 {

    @Test
    fun `day 13 part 1 sample`() {
        val pointsAndFold = getInstance("day13_sample").toDay13Instance()
        val displayedPoint = foldTransparentPaper(pointsAndFold.first, pointsAndFold.second, 1)
        assertEquals(17, displayedPoint.size)
    }


    @Test
    fun `day 13 part 1`() {
        val pointsAndFold = getInstance("day13").toDay13Instance()
        val displayedPoint = foldTransparentPaper(pointsAndFold.first, pointsAndFold.second, 1)
        print(displayedPoint.size)
    }

    @Test
    fun `day 13 part 2`() {
        val pointsAndFold = getInstance("day13").toDay13Instance()
        val displayedPointCount = foldTransparentPaper(pointsAndFold.first, pointsAndFold.second)
        printAll(displayedPointCount)
    }

}

private fun List<String>.toDay13Instance(): Pair<List<Point>, List<FoldInstruction>> {
    val points = this.filter { it.contains(",") }
        .map { it.toPoint() }
    val foldInstructions = this.filter { it.contains("fold") }
        .map { it.toFoldInstruction() }
    return Pair(points, foldInstructions)
}


private fun printAll(displayedPointCount: Set<Point>) {
    println("")
    for (y in 0..10) {
        for(x in 0..50) {
            if(displayedPointCount.contains(Point(x, y))){
                print(" # ")
            } else {
                print(" . ")
            }
        }
        print("\n")
    }
}