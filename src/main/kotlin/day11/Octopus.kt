package day11

data class Octopus(
    var energy: Int,
    var isFlashing: Boolean = false,
    var hasFlashed: Boolean = false,
    val neighbours: MutableList<Octopus> = mutableListOf()
) {
    fun reset() {
        if (hasFlashed) {
            energy = 0
            hasFlashed = false
        }
    }

}

object OctopusBehaviour {
    fun initNeighbour(octopuses: List<List<Octopus>>) {
        for (i in octopuses.indices) {
            for (j in octopuses[i].indices) {
                if (i > 0) {
                    octopuses[i][j].neighbours.add(octopuses[i - 1][j])
                    if (j > 0) {
                        octopuses[i][j].neighbours.add(octopuses[i - 1][j - 1])
                    }
                    if (j < octopuses[i].size - 1) {
                        octopuses[i][j].neighbours.add(octopuses[i - 1][j + 1])
                    }
                }
                if (i < octopuses.size - 1) {
                    octopuses[i][j].neighbours.add(octopuses[i + 1][j])
                    if (j > 0) {
                        octopuses[i][j].neighbours.add(octopuses[i + 1][j - 1])
                    }
                    if (j < octopuses[i].size - 1) {
                        octopuses[i][j].neighbours.add(octopuses[i + 1][j + 1])
                    }
                }
                if (j > 0) {
                    octopuses[i][j].neighbours.add(octopuses[i][j - 1])
                }
                if (j < octopuses[i].size - 1) {
                    octopuses[i][j].neighbours.add(octopuses[i][j + 1])
                }

            }
        }
    }

    fun runSteps(steps: Int, octopuses: List<List<Octopus>>): Int {
        var flashCount = 0

        for (i in 0 until steps) {
            var isAnyFlashing: Boolean

            octopuses.map {
                it.map { oc ->
                    oc.energy += 1
                    if (oc.energy > 9) {
                        oc.isFlashing = true
                        flashCount++
                    }
                }
            }

            isAnyFlashing = octopuses.flatten().any { it.isFlashing }

            while (isAnyFlashing) {
                isAnyFlashing = false
                octopuses.flatten().map {
                    if (it.isFlashing) {
                        it.neighbours.map { n -> n.energy += 1 }
                    }
                    it.hasFlashed = it.hasFlashed || it.isFlashing
                    it.isFlashing = false
                }
                octopuses.map {
                    it.map { oc ->
                        if (oc.energy > 9 && !oc.hasFlashed) {
                            isAnyFlashing = true
                            oc.isFlashing = true
                            flashCount++
                        }
                    }
                }
            }

            octopuses.map {
                it.map { oc ->
                    oc.reset()
                }
            }
        }

        return flashCount
    }


    fun countSteps(octopuses: List<List<Octopus>>): Int {
        var isSync = false
        var count = 0
        while (!isSync) {
            var isAnyFlashing: Boolean

            octopuses.map {
                it.map { oc ->
                    oc.energy += 1
                    if (oc.energy > 9) {
                        oc.isFlashing = true
                    }
                }
            }

            isAnyFlashing = octopuses.flatten().any { it.isFlashing }

            while (isAnyFlashing) {
                isAnyFlashing = false
                octopuses.flatten().map {
                    if (it.isFlashing) {
                        it.neighbours.map { n -> n.energy += 1 }
                    }
                    it.hasFlashed = it.hasFlashed || it.isFlashing
                    it.isFlashing = false
                }
                octopuses.map {
                    it.map { oc ->
                        if (oc.energy > 9 && !oc.hasFlashed) {
                            isAnyFlashing = true
                            oc.isFlashing = true
                        }
                    }
                }
            }

            octopuses.map {
                it.map { oc ->
                    oc.reset()
                }
            }
            count++
            isSync = octopuses.flatten().all { it.energy == 0 }

        }
        return count
    }
}
