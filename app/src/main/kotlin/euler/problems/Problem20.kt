package euler.problems

import euler.BigInteger
import euler.EulerProblem
import java.math.BigInteger

object Problem20 : EulerProblem() {
    override val name = "Factorial Digit Sum"

    fun factorial(input: BigInteger): BigInteger {
        var out = BigInteger.ONE
        var current = input
        while (current > BigInteger.ZERO) out *= current--
        return out
    }

    override fun exampleProblem(): String = buildString {
        append("Find the sum of the digits of 10!: ")
        val result = factorial(BigInteger(10)).toString().map { it.code - 48 }
        append(result.sum())
    }

    override fun realProblem(): String = buildString {
        append("Find the sum of the digits of 100!: ")
        val result = factorial(BigInteger(100)).toString().map { it.code - 48 }
        append(result.sum())    }
}

fun main() {
    println(Problem20.solve())
}