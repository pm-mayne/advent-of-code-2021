package day02

object SubmarinePosition {

    fun getPosition(instance: List<String>): Position {
        return instance.map { it.toPosition() }
            .reduce { a, b -> Position(a.depth + b.depth, a.horizontal + b.horizontal) }
    }

    fun getPosition2(instance: List<String>): Position {
        var aim = 0
        return instance.map { it.toPosition() }
            .reduce { a, b ->
                aim += b.depth
                Position(a.depth + aim * b.horizontal, a.horizontal + b.horizontal)
            }
    }
}

private fun String.toPosition(): Position {
    val split = this.split(" ")
    return when (split[0]) {
        "forward" -> Position(horizontal = split[1].toInt())
        "up" -> Position(depth = -split[1].toInt())
        "down" -> Position(depth = split[1].toInt())
        else -> Position()
    }

}


data class Position(val depth: Int = 0, val horizontal: Int = 0)
