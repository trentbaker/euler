package euler.problems

import euler.BigInteger
import euler.EulerProblem
import java.math.BigInteger

object Problem74 : EulerProblem() {
    override val name = "Digit Factorial Chains"

    private fun digitFactorialChain(start: BigInteger = BigInteger.ONE): Sequence<BigInteger> = generateSequence(start) { input ->
        input.toString().map { digit -> Problem20.factorial(BigInteger(digit.code - 48)) }.sumOf { it }
    }.drop(1)

    private fun digitFactorialChainUntilNotUnique(start: BigInteger): List<BigInteger> {
        var current = start
        val chain = mutableListOf(current)
        do {
            current = digitFactorialChain(current).first()
            chain.add(current)
        } while (!Problem34.digitFactorialLoopFlags.map { it.first }.contains(current))
        return chain.toList() + (Problem34.digitFactorialLoopFlags.firstOrNull { it.first == chain.last() }?.second
            ?: listOf())
    }

    override fun realProblem(): String = buildString {
        append("How many digit factorial chains, with a starting number below one million, contain exactly sixty non-repeating terms: ")
        val result = (0 until 1000000).asSequence().map {
            digitFactorialChainUntilNotUnique(BigInteger(it))
        }
        append(result.count { it.size == 60 })
    }
}

fun main() {
    println(Problem74.solve())
}