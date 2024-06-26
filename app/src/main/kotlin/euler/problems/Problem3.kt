package euler.problems

import euler.BigInteger
import euler.EulerProblem
import java.math.BigInteger
import kotlin.math.sqrt

object Problem3 : EulerProblem() {
    override val name = "Largest Prime Factor"

    fun primesBelow(max: Int): List<BigInteger> {
        val result = (listOf(2) + (3..max step 2)).toMutableList()
        val divisors = (2..sqrt(max.toDouble()).toInt())

        divisors.onEach { divisor -> result.removeIf { it % divisor == 0 && it != divisor } }

        return result.map { BigInteger(it) }
    }

    private fun primeFactors(input: BigInteger): List<BigInteger> {
        if (input == BigInteger.ONE) return listOf(input)

        // may need to increase the starting list of primes... setting a const for now
        // but only checking primes less than input
        var relevantPrimes = primesBelow(10000)
            .filter { it <= input }
            .toMutableList()
        val output: MutableList<BigInteger> = mutableListOf()
        var current = input

        // this could cause problems with big primes
        if (relevantPrimes.contains(input)) return listOf(input)

        while ((output.reduce { a, b -> a * b }) != input) {
            if (relevantPrimes.isEmpty()) throw Exception("might need more primes")
            while (current % relevantPrimes.first() == BigInteger.ZERO) {
                current /= relevantPrimes.first()
                output.add(relevantPrimes.first())
            }
            relevantPrimes = relevantPrimes.drop(1).toMutableList()
        }

        return output
    }

    override fun exampleProblem() = buildString {
        append("Find the largest prime factor of 13195: ")
        val factors = primeFactors(BigInteger(13195))
        append(factors.maxOf { it })
    }

    override fun realProblem() = buildString {
        append("Find the largest prime factor of 600851475143: ")
        val factors = primeFactors(BigInteger("600851475143"))
        append(factors.maxOf { it })
    }
}

fun main() {
    println(Problem3.solve())
}