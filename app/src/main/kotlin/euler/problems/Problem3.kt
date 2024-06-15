package euler.problems

import euler.EulerProblem
import euler.primesBelow
import euler.product
import java.math.BigInteger

object Problem3 : EulerProblem() {
    private fun primeFactors(input: BigInteger): List<BigInteger> {
        if (input == BigInteger.ONE) return listOf(input)

        // may need to increase the starting list of primes... setting a const for now
        // but only checking primes less than input
        var relevantPrimes = primesBelow(10000)
            .map { it.toBigInteger() }
            .filter { it <= input }
            .toMutableList()
        val output: MutableList<BigInteger> = mutableListOf()
        var current = input

        // this could cause problems with big primes
        if (relevantPrimes.contains(input)) return listOf(input)

        while (product(output.toList()) != input) {
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