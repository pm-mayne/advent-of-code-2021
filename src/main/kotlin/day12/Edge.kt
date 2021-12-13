package day12

data class Edge(val cave1: Cave, val cave2: Cave) {

    fun getDestination(fromCave: String): Cave {
        return if (fromCave == cave1.id) {
            cave2
        } else {
            cave1
        }
    }
}
