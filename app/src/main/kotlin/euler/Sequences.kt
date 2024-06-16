package euler

import euler.problems.Problem20
import java.math.BigInteger

fun triangleNumber(input: Int): Int = IntRange(0, input).sum()

fun digitFactorialChain(start: Int) = digitFactorialChain(start.toBigInteger())

fun BigInteger.factorial() = Problem20.factorial(this)

fun digitFactorialChain(start: BigInteger = BigInteger.ONE) = generateSequence(start) { input ->
    input.toString().toList().map { digit -> (digit.code - 48).toBigInteger().factorial() }.sumOf { it }
}.drop(1)

fun lexicographicSequence(input: String) = generateSequence(input.toList().sorted()) { previous ->
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
}
