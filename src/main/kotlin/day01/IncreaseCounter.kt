package day01

object IncreaseCounter {

    fun countIncreases(instance: List<Int>): Int {
        var increaseCount = 0

        for (i in 1 until instance.size) {
            if (instance[i] - instance[i - 1] > 0) {
                increaseCount++;
            }
        }

        return increaseCount
    }

    fun countIncreasesOnSlidingWindow(instance: List<Int>, windowSize: Int): Int {
        var increaseCount = 0

        for (i in windowSize until instance.size) {
            if (instance[i] - instance[i - windowSize] > 0) {
                increaseCount++;
            }
        }
        return increaseCount
    }
}