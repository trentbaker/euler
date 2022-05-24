package euler

import java.math.BigInteger

fun triangleNumbers(start: Int = 1): Sequence<Int> = generateSequence(Pair(start, triangleNumber(start))) {
    Pair(it.first + 1, it.second + it.first + 1)
}.map { it.second }

fun triangleNumber(input: Int): Int = IntRange(0, input).sum()

fun collatzSequence(start: Int): Sequence<BigInteger> = collatzSequence(start.toBigInteger())

fun collatzSequence(start: BigInteger): Sequence<BigInteger> = generateSequence(start) {
    if (it == BigInteger.ONE) null else {
        if (it.mod(BigInteger.TWO) == BigInteger.ZERO) {
            it.div(BigInteger.TWO)
        } else (it.times(3.toBigInteger())) + BigInteger.ONE
    }
}

fun fibonacciSequence(start: Pair<Int, Int> = 0 to 1) =
    bigFibonacciSequence(start.first.toBigInteger() to start.second.toBigInteger())
        .map { it.toInt() }

fun bigFibonacciSequence(start: Pair<BigInteger, BigInteger> = BigInteger.ZERO to BigInteger.ONE) =
    generateSequence(start) {
        it.second to it.first + it.second
    }.map { it.first }

fun digitFactorialChain(start: Int) = digitFactorialChain(start.toBigInteger())

fun digitFactorialChain(start: BigInteger = BigInteger.ONE) = generateSequence(start) { input ->
    input.toString().toList().map { digit -> (digit.code - 48).toBigInteger().factorial() }.sum()
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
