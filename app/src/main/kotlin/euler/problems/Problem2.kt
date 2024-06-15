package euler.problems

import euler.EulerProblem
import java.math.BigInteger

object Problem2 : EulerProblem() {
    private val fibonacciSequence = generateSequence(BigInteger(0) to BigInteger(1)) { previous ->
        previous.second to previous.second + previous.first
    }.map { it.first }

    override fun exampleProblem(): String = buildString {
        append("Find the sum of all even-valued Fibonacci numbers below 100: ")
        val result = fibonacciSequence.filter { it.mod(BigInteger.TWO) == BigInteger.ZERO }
            .takeWhile { it < BigInteger(100) }
            .sumOf { it }
        append(result)
    }
    override fun realProblem(): String = buildString {
        append("Find the sum of all even-valued Fibonacci numbers below four million: ")
        val result = fibonacciSequence.filter { it.mod(BigInteger.TWO) == BigInteger.ZERO }
            .takeWhile { it < BigInteger(4000000) }
            .sumOf { it }
        append(result)
    }
}

fun main () {
    println(Problem2.solve())
}