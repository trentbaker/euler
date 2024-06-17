package euler.problems

import euler.EulerProblem

object Problem24 : EulerProblem() {
    override val name = "Lexicographic Permutations"

    private fun lexicographicSequence(input: String) = generateSequence(input.toList().sorted()) { previous ->
        if (previous.sortedDescending() == previous) null else {

            // rightmost character in it, which is smaller than its next character
            val firstIndex = previous.mapIndexed { index, c ->
                index to ((previous.getOrNull(index + 1)?.compareTo(c) ?: 0) > 0)
            }.last { it.second }.first

            val firstCharacter = previous[firstIndex]

            val secondCharacter = previous
                .drop(firstIndex + 1) // must be to the right of the first
                .filter { it > firstCharacter } // must be greater than the first
                .minOrNull() // the smallest of these
                ?: '?' // default

            val secondIndex = previous.indexOf(secondCharacter)

            previous.toMutableList().apply {
                set(firstIndex, secondCharacter)
                set(secondIndex, firstCharacter)
            }.run {
                take(firstIndex + 1) + drop(firstIndex + 1).sorted()
            }
        }
    }.map { it.joinToString("") }

    override fun exampleProblem(): String = buildString {
        append("Find the 6 lexicographical permutations of 0, 1, and 2: ")
        lexicographicSequence("012").onEach { append(" $it ") }.toList()
    }

    override fun realProblem(): String = buildString {
        append("Find the millionth lexicographical permutations of the digits 0-9: ")
        val millionth = lexicographicSequence("0123456789").elementAt(999999)
        append(millionth)
    }
}

fun main() {
    println(Problem24.solve())
}