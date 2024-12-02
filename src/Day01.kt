import kotlin.math.absoluteValue

fun main() {
    fun part1(input: List<String>): Int {
        val (leftColumn, rightColumn) = input.parse().let { it.first.sorted() to it.second.sorted() }
        var result = 0
        for (i in leftColumn.indices) {
            result += (rightColumn[i] - leftColumn[i]).absoluteValue
        }
        return result
    }

    fun part2(input: List<String>): Int {
        val (leftColumn, rightColumn) = input.parse()
        var result = 0
        leftColumn.forEach {
            val number = it
            val occurrences = rightColumn.count { it == number }
            result += number * occurrences
        }
        return result
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 11)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}

private fun List<String>.parse() = map {
    val split = it.split("   ")
    split.first().toInt() to split[1].toInt()
}.let {
    val leftColumn = it.map { it.first }
    val rightColumn = it.map { it.second }
    leftColumn to rightColumn
}
