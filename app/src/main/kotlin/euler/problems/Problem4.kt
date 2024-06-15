package euler.problems

import euler.EulerProblem
import kotlin.math.floor

object Problem4 : EulerProblem() {
    private fun Int.isPalindromic(): Boolean {
        var half = floor(this.toString().length / 2.0).toInt()

        val firstHalf = this.toString().substring(0, half)
        if (this.toString().length % 2 != 0) half++
        val secondHalf = this.toString().substring(half).reversed()

        return firstHalf == secondHalf
    }

    private val twoDigitNumbers = (10 until 100).asSequence()
    private val threeDigitNumbers = (100 until 1000).asSequence()

    override fun exampleProblem() = buildString {
        append("Find the largest palindrome made from the Product of two 2-digit numbers: ")
        val palindromeProducts = twoDigitNumbers.flatMap { x ->
            twoDigitNumbers.map { y -> x * y }
                .filter { it.isPalindromic() }
        }
        append(palindromeProducts.maxOf { it })
    }

    override fun realProblem() = buildString {
        append("Find the largest palindrome made from the Product of two 3-digit numbers: ")
        val palindromeProducts = threeDigitNumbers.flatMap { x ->
            threeDigitNumbers.map { y -> x * y }
                .filter { it.isPalindromic() }
        }
        append(palindromeProducts.maxOf { it })
    }

}

fun main() {
    println(Problem4.solve())
}