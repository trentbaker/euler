package euler.problems

import euler.EulerProblem

object Problem191 : EulerProblem() {
    override val name = "Prize Strings"

    private fun isPrizeString(s: String) = s.count { it == 'L' } < 2 && !s.contains("AAA")

    fun enumerateStrings(library: String, length: Int): Sequence<String> {
        val alphabet = library.toSortedSet().toList()
        return generateSequence(List(length) { 0 }) { previous ->
            val incrementingIndex = previous.indexOfLast { it < (alphabet.size - 1) }

            if (incrementingIndex < 0) return@generateSequence null
            previous.take(incrementingIndex) +
                    (previous[incrementingIndex] + 1) +
                    List(length - (incrementingIndex + 1)) { 0 }
        }.map { it.joinToString("") { "${alphabet[it]}" } }
    }

    data class AbsentTrackerState(
        val day: Int, // n
        val prizeStrings: Int, // t
        val endingInAA: Int,// a
        val zeroLs: Int, // b
        val endingInA: Int, // c
        val endingInAAWithOneL: Int, // d
        val endingInAWithOneL: Int, // e
        val notEndingInAOneL: Int, // f
    ) {
        // on the first day, all possible strings are ["O", "A", "L"]
        // - 3 are prizeStrings
        // - 0 end with AA
        // - 2 contain zero L
        // - 1 ends with A
        // - 0 end with AA and contain one L
        // - 0 end with A and contain one L
        // - 1 doesn't end in A and contain one L
        constructor() : this(1, 3, 0, 2, 1, 0, 0, 1)

        companion object {
            val daySequence = generateSequence(AbsentTrackerState()) { previous ->
                with(previous) {
                    AbsentTrackerState(
                        day = day + 1,
                        prizeStrings = 2 * prizeStrings + zeroLs - endingInAA,
                        endingInAA = endingInA,
                        zeroLs = 2 * zeroLs - endingInAA + endingInAAWithOneL,
                        endingInA = prizeStrings - (endingInAA + endingInA),
                        endingInAAWithOneL = endingInAWithOneL,
                        endingInAWithOneL = notEndingInAOneL,
                        notEndingInAOneL = prizeStrings
                    )
                }
            }
        }
    }

    override fun exampleProblem(): String = buildString {
        append("Find the 43 prize strings across a 4 day period: ")
        val prizeStrings = enumerateStrings("OLA", 4)
            .filter { isPrizeString(it) }

        append(prizeStrings.toList())
    }

    override fun realProblem(): String = buildString {
        append("How many prize strings exist across a 30 day period: ")
        // brute forcing this would take far too long, use math
        val result = AbsentTrackerState.daySequence.elementAt(29)
        append(result.prizeStrings)
    }
}

fun main() {
    println(Problem191.solve())
}