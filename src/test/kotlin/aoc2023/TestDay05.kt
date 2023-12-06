package aoc2023

import aoc2023.day05.Almanac
import aoc2023.day05.RangedMapper
import org.junit.Test
import util.Util
import kotlin.math.min

class TestDay05 {

    private val example = listOf(
        "seeds: 79 14 55 13",
        "",
        "seed-to-soil map:",
        "50 98 2",
        "52 50 48",
        "",
        "soil-to-fertilizer map:",
        "0 15 37",
        "37 52 2",
        "39 0 15",
        "",
        "fertilizer-to-water map:",
        "49 53 8",
        "0 11 42",
        "42 0 7",
        "57 7 4",
        "",
        "water-to-light map:",
        "88 18 7",
        "18 25 70",
        "",
        "light-to-temperature map:",
        "45 77 23",
        "81 45 19",
        "68 64 13",
        "",
        "temperature-to-humidity map:",
        "0 69 1",
        "1 0 69",
        "",
        "humidity-to-location map:",
        "60 56 37",
        "56 93 4",
    )

    @Test
    fun `day 05 part 1`() {
        val instance = Util.getInstance("aoc2023/day05").getInstance()
        //val instance = example.getInstance()

        val locations = instance.seeds
            .asSequence()
            .map { instance.seedToSoil.remap(it) }
            .map { instance.soilToFertilizer.remap(it) }
            .map { instance.fertilizerToWater.remap(it) }
            .map { instance.waterToLight.remap(it) }
            .map { instance.lightToTemperature.remap(it) }
            .map { instance.temperatureToHumidity.remap(it) }
            .map { instance.humidityToLocation.remap(it) }
            .toList()

        println("Min location number: ${locations.minByOrNull { it!! }}")

    }

    @Test
    fun `day 05 part 2`() {
        val instance = Util.getInstance("aoc2023/day05").getInstance()
        //val instance = example.getInstance()

        var minLoc: Long? = null

        for (i in instance.seeds.indices step 2) {
            println("***Seed: $i")
            for (rangeIndex in 0..instance.seeds[i + 1]) {
                val seed = instance.seeds[i] + rangeIndex
                val soil = instance.seedToSoil.remap(seed)
                val fert = instance.soilToFertilizer.remap(soil)
                val water = instance.fertilizerToWater.remap(fert)
                val light = instance.waterToLight.remap(water)
                val temp = instance.lightToTemperature.remap(light)
                val hum = instance.temperatureToHumidity.remap(temp)
                val loc = instance.humidityToLocation.remap(hum)
                minLoc = if (minLoc == null) {
                    loc
                } else {
                    if(loc < minLoc) {
                        println("Better MinLoc: $loc")
                    }
                    min(loc, minLoc)
                }
            }
        }

        println("Min location number: $minLoc")

    }


}

private fun List<RangedMapper>.remap(index: Long): Long {
    return this.mapNotNull { it.remap(index) }
        .firstOrNull() ?: index
}

private fun List<String>.getInstance(): Almanac {
    var addMode = ""
    var seeds = mutableListOf<Long>()
    val seedToSoil: MutableList<RangedMapper> = mutableListOf()
    val soilToFertilizer: MutableList<RangedMapper> = mutableListOf()
    val fertilizerToWater: MutableList<RangedMapper> = mutableListOf()
    val waterToLight: MutableList<RangedMapper> = mutableListOf()
    val lightToTemperature: MutableList<RangedMapper> = mutableListOf()
    val temperatureToHumidity: MutableList<RangedMapper> = mutableListOf()
    val humidityToLocation: MutableList<RangedMapper> = mutableListOf()

    for (line in this) {
        when {
            line.contains("seeds:") -> {
                seeds.addAll(line.split(": ")[1].split(" ").map { it.toLong() })
            }
            line.any { it.isDigit() } -> {
                when (addMode) {
                    "seed-to-soil map:" -> seedToSoil.parseRange(line)
                    "soil-to-fertilizer map:" -> soilToFertilizer.parseRange(line)
                    "fertilizer-to-water map:" -> fertilizerToWater.parseRange(line)
                    "water-to-light map:" -> waterToLight.parseRange(line)
                    "light-to-temperature map:" -> lightToTemperature.parseRange(line)
                    "temperature-to-humidity map:" -> temperatureToHumidity.parseRange(line)
                    "humidity-to-location map:" -> humidityToLocation.parseRange(line)
                }
            }
            line.contains(":") -> {
                addMode = line
            }
        }
    }
    return Almanac(
        seeds = seeds,
        seedToSoil = seedToSoil,
        soilToFertilizer = soilToFertilizer,
        fertilizerToWater = fertilizerToWater,
        waterToLight = waterToLight,
        lightToTemperature = lightToTemperature,
        temperatureToHumidity = temperatureToHumidity,
        humidityToLocation = humidityToLocation
    )

}

private fun MutableList<RangedMapper>.parseRange(line: String) {
    val ranges = line.split(" ").map { it.toLong() }
    val destinationRangeStart = ranges[0]
    val sourceRangeStart = ranges[1]
    val rangeLength = ranges[2]
    this.add(RangedMapper(destinationRangeStart, sourceRangeStart, rangeLength))
}
