package euler.problems

import euler.EulerProblem
import java.math.BigInteger

object Problem14 : EulerProblem() {

     private fun collatzSequence(start: BigInteger): Sequence<BigInteger> = generateSequence(start) {
        if (it == BigInteger.ONE) null else {
            if (it.mod(BigInteger.TWO) == BigInteger.ZERO) {
                it.div(BigInteger.TWO)
            } else (it.times(3.toBigInteger())) + BigInteger.ONE
        }
    }

    override fun realProblem(): String = buildString {
        append("Which number, under one million, produces the longest collatz chain: ")
        val result = (1 until 1000000).maxBy { collatzSequence(BigInteger(it)).count() }
        append(result)
    }
}

fun main() {
    println(Problem14.solve())
}