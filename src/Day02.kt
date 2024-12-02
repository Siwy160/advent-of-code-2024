import kotlin.math.absoluteValue

fun main() {
    fun part1(input: List<String>): Int {
        val reports = input.parse()
        var result = 0
        reports.forEach {
            val isValidForward = isValid(it)
            if (isValidForward) {
                result++
            }
        }
        return result
    }

    fun part2(input: List<String>): Int {
        val reports = input.parse()
        var result = 0
        reports.forEach {
            val isValidForward = isValid(it)
            if (isValidForward) {
                result++
                return@forEach
            }
            for (i in it.indices) {
                val valid = isValid(it.filterIndexed { index, _ -> index != i })
                if (valid) {
                    result++
                    return@forEach
                }
            }
        }
        return result
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 2)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day02")
    part1(input).println()
    check(part2(testInput) == 4)
    part2(input).println()
}

private fun List<String>.parse() = map {
    it.split(" ").map { it.toInt() }
}


fun isValid(report: List<Int>): Boolean {
    var isRising: Boolean? = null
    report.forEachIndexed { index, i ->
        val difference = (i - (report.getOrNull(index + 1) ?: return true))
        if (difference == 0) return false
        if (isRising == null) {
            isRising = difference < 0
        }
        isRising?.let { isRising ->
            if (difference.absoluteValue > 3) return false
            if (difference > 0 && isRising) return false
            if (difference < 0 && isRising.not()) return false
        }
    }
    return true
}