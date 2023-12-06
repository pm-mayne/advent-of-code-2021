package aoc2023.day05

data class Almanac(
    val seeds: List<Long>,
    val seedToSoil: List<RangedMapper>,
    val soilToFertilizer: List<RangedMapper>,
    val fertilizerToWater: List<RangedMapper>,
    val waterToLight: List<RangedMapper>,
    val lightToTemperature: List<RangedMapper>,
    val temperatureToHumidity: List<RangedMapper>,
    val humidityToLocation: List<RangedMapper>
)

data class RangedMapper(val destinationStartIndex: Long, val sourceStartIndex: Long, val range: Long) {

    public fun remap(source: Long): Long? {
        return if (source >= sourceStartIndex && source < sourceStartIndex + range) {
            destinationStartIndex + source - sourceStartIndex
        } else null
    }
}

