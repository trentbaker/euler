package euler.problems

import euler.BigInteger
import euler.EulerProblem
import java.math.BigInteger

object Problem30 : EulerProblem() {
    override val name = "Digit Fifth Powers"

    private fun digitPowerBound(power: Int) = BigInteger(9).pow(power).let {
        // 9^power in all digits is the max possible number that could matter
        it * BigInteger(it.toString().length)
    }

    private fun digitPowers(p: Int) = generateSequence(BigInteger.TWO) {
        if (it < digitPowerBound(p)) it + BigInteger.ONE
        else null
    }.filter { it.toString().sumOf { BigInteger(it.digitToInt()).pow(p) } == it }

    private val digitFourthPowers = digitPowers(4)
    private val digitFifthPowers = digitPowers(5)

    override fun exampleProblem(): String = buildString {
        append("Find the sum of all numbers that are equal to the sum of the fourth power of it's digits: ")
        val powers = digitFourthPowers
        append(powers.sumOf { it })
    }

    override fun realProblem(): String = buildString {
        append("Find the sum of all numbers that are equal to the sum of the fifth power of it's digits: ")
        val powers = digitFifthPowers
        append(powers.sumOf { it })
    }
}

fun main() {
    println(Problem30.solve())
}