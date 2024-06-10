package euler.problems

import euler.problems.Problem191.isPrizeString
import euler.timed

/**
 * A particular school offers cash rewards to children with good attendance and punctuality. If they are absent for three consecutive days or late on more than one occasion then they forfeit their prize.
 *
 * During an n-day period a trinary string is formed for each child consisting of L's (late), O's (on time), and A's (absent).
 *
 * Although there are eighty-one trinary strings for a 4-day period that can be formed, exactly forty-three strings would lead to a prize:
 *
 * OOOO OOOA OOOL OOAO OOAA OOAL OOLO OOLA OAOO OAOA
 * OAOL OAAO OAAL OALO OALA OLOO OLOA OLAO OLAA AOOO
 * AOOA AOOL AOAO AOAA AOAL AOLO AOLA AAOO AAOA AAOL
 * AALO AALA ALOO ALOA ALAO ALAA LOOO LOOA LOAO LOAA
 * LAOO LAOA LAAO
 *
 * How many "prize" strings exist over a 30-day period?
 */
object Problem191 {
    fun isPrizeString(s: String) = s.count { it == 'L' } < 2 && !s.contains("AAA")
    fun enumerateStrings(alphabet: List<Char>, length: Int): Sequence<String> {
        if (length <= 0) return generateSequence { null }

        return generateSequence(List(length) { 0 }) { indices ->
            // Find the position to increment
            var pos = length - 1
            while (pos >= 0 && indices[pos] == alphabet.size - 1) {
                pos--
            }

            if (pos < 0) {
                // If we've incremented past the first position, we're done
                null
            } else {
                // Increment the current position and reset all positions to the right
                indices.toMutableList().also { next ->
                    next[pos]++
                    for (i in pos + 1 until length) {
                        next[i] = 0
                    }
                }
            }
        }.map { indices ->
            // Convert indices to string
            indices.map { alphabet[it] }.joinToString("")
        }
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
}

fun main() {
    /**
     * brute forcing because string enumeration is interesting.
     * solving the 30-day case would need to check something like
     * 205 trillion possible strings which is not feasible
     */
    timed {
        val possibleStrings = Problem191.enumerateStrings("OAL".toList(), 4)
        val prizeStrings = possibleStrings
            .filter { isPrizeString(it) }

        println("\nAcross a 4 day period, there are ${prizeStrings.count()} prize strings")
    }
    timed {
        val period = 30
        val out = Problem191.AbsentTrackerState.daySequence.elementAt(period - 1)

        println("Across a $period day period, there are ${out.prizeStrings} prize strings")
    }

}