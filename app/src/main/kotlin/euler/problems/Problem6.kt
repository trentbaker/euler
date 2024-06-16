package euler.problems

import euler.EulerProblem
import java.math.BigInteger

object Problem6: EulerProblem() {
    override val name = "Sum Square Difference"
    private fun squareOfSum(input: IntRange): BigInteger = input.sum().toBigInteger().pow(2)

    private fun sumOfSquares(input: IntRange): BigInteger {
        var output = BigInteger.ZERO
        input.map { it.toBigInteger().pow(2) }.forEach { output = output.plus(it) }
        return output
    }
    override fun exampleProblem(): String = buildString {
        append("Find the difference between the sum of the squares of the first ten natural numbers and the square of the sum: ")
        val result = squareOfSum(1..10) - sumOfSquares(1 ..10)
        append(result)
    }

    override fun realProblem(): String = buildString {
        append("Find the difference between the sum of the squares of the first one hundred natural numbers and the square of the sum: ")
        val result = squareOfSum(1..100) - sumOfSquares(1 ..100)
        append(result)
    }
}

fun main(){
    println(Problem6.solve())
}