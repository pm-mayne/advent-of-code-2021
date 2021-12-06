package day06

data class LanternFish(var age: Int) {
    fun age(): LanternFish? {
        age--
        if (age < 0) {
            age = 6
            return LanternFish(8)
        }
        return null
    }
}

object LanternFishLifecycle {
    fun cycleNDays(n: Int, school: MutableList<LanternFish>) {
        for (i in 0 until n) {
            val babyFishes = mutableListOf<LanternFish>()
            school.map {
                val baby = it.age()
                if (baby != null) {
                    babyFishes.add(baby)
                }
            }
            school.addAll(babyFishes)
        }
    }

    fun computeLifeCycleSmart(n: Int, school: List<LanternFish>): Long {
        val spawnsAtDay = mutableListOf<Long>()
        spawnsAtDay.add(0)
        spawnsAtDay.add(0)
        spawnsAtDay.add(0)
        spawnsAtDay.add(0)
        spawnsAtDay.add(0)
        spawnsAtDay.add(0)
        spawnsAtDay.add(0)

        val newSpawns = mutableListOf<Long>()
        newSpawns.add(0)
        newSpawns.add(0)
        newSpawns.add(0)
        newSpawns.add(0)
        newSpawns.add(0)
        newSpawns.add(0)
        newSpawns.add(0)

        school.map { spawnsAtDay[it.age + 1]++ }

        for (day in 1..n) {
            newSpawns[(day + 2) % 7] = spawnsAtDay[day % 7]
            spawnsAtDay[day % 7] += newSpawns[day % 7]
            newSpawns[day % 7] = 0
        }
        return spawnsAtDay.reduce { a, b -> a + b } + newSpawns.reduce { a, b -> a + b }
    }
}
