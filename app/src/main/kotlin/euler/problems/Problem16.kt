package euler.problems

import euler.EulerProblem
import java.math.BigDecimal

object Problem16 : EulerProblem() {
    override val name = "Power Digit Sum"
    override fun exampleProblem(): String = buildString {
        append("Find the sum of the digits of 2^15: ")
        val result = BigDecimal(2).pow(15).toString().map { it.code - 48 }.sum()
        append(result)
    }

    override fun realProblem(): String = buildString {
        append("Find the sum of the digits of 2^1000: ")
        val result = BigDecimal(2).pow(1000).toString().map { it.code - 48 }.sum()
        append(result)
    }
}

fun main() {
    println(Problem16.solve())
}