package euler

import euler.problems.Problem20
import java.math.BigInteger

fun triangleNumber(input: Int): Int = IntRange(0, input).sum()

fun digitFactorialChain(start: Int) = digitFactorialChain(start.toBigInteger())

fun BigInteger.factorial() = Problem20.factorial(this)

fun digitFactorialChain(start: BigInteger = BigInteger.ONE) = generateSequence(start) { input ->
    input.toString().toList().map { digit -> (digit.code - 48).toBigInteger().factorial() }.sumOf { it }
}.drop(1)


