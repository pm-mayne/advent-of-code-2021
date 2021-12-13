package day13

object TransparentPaperFolding {

    fun String.toPoint(): Point {
        return Point(this.split(",")[0].toInt(), this.split(",")[1].toInt())
    }

    fun String.toFoldInstruction(): FoldInstruction {
        val split = this.split("=")
        return if (split[0] == "fold along x") {
            LeftFold(split[1].toInt())
        } else {
            UpFold(split[1].toInt())
        }
    }


    fun foldTransparentPaper(
        points: List<Point>,
        foldInstructions: List<FoldInstruction>,
        foldCount: Int = foldInstructions.size
    ): Set<Point> {
        var currentSheet = points.toSet()
        val result = foldInstructions
            .take(foldCount)
            .map { f ->
                val newSheet = currentSheet
                    .map { p -> f.fold(p) }
                    .toSet()
                currentSheet = newSheet
                newSheet
            }
            .last()

        return result
    }
}

class LeftFold(private val x: Int) : FoldInstruction {
    override fun fold(point: Point): Point {
        return Point(x = newX(point.x), y = point.y)
    }

    private fun newX(pointX: Int): Int {
        return if (pointX < x) {
            pointX
        } else {
            x - (pointX - x)
        }
    }


}

class UpFold(val y: Int) : FoldInstruction {
    override fun fold(point: Point): Point {
        return Point(x = point.x, y = newY(point.y))
    }

    private fun newY(pointY: Int): Int {
        return if (pointY < y) {
            pointY
        } else {
            y - (pointY - y)
        }
    }

}

interface FoldInstruction {
    fun fold(point: Point): Point
}

data class Point(val x: Int, val y: Int) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Point

        if (x != other.x) return false
        if (y != other.y) return false

        return true
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        return result
    }
}
