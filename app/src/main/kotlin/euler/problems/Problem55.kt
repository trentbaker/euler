package euler.problems

import euler.BigInteger
import euler.EulerProblem
import java.math.BigInteger
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.roundToInt

object Problem55 : EulerProblem() {
    override val name = "Lychrel Numbers"

    private val lychrelNumbers = generateSequence(BigInteger.ONE) { it + BigInteger.ONE }.filter { isLychrelNumber(it) }

    private fun isLychrelNumber(input: BigInteger): Boolean {
        // 50 is magic number from problem
        (0..50).foldIndexed(input) { index, current, _ ->
            val palindromic = palindromic(current)

            if (palindromic && index > 0) return false // not a lychrel number
            val reversed = current.toString().reversed().toBigInteger()
            val next = current + reversed
            next
        }
        return true // is a lychrel number
    }

    private fun palindromic(input: BigInteger): Boolean {
        val inputString = input.toString()
        if (inputString.length == 1) return true
        val half = (inputString.length / 2.0)

        val firstHalf = inputString.substring(0, floor(half).toInt())
        val secondHalf = inputString.substring(ceil(half).toInt())

        return firstHalf == secondHalf.reversed()
    }

    override fun realProblem(): String = buildString {
        append("How many Lychrel numbers are there below ten-thousand: ")
        val numbers = lychrelNumbers.takeWhile { it < BigInteger(10000) }

        append(numbers.count())
    }
}

fun main() {
    println(Problem55.solve())
}