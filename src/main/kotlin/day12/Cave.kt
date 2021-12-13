package day12

data class Cave(val id: String, val isBig : Boolean = id.toUpperCase() == id)
