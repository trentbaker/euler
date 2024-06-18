package euler.problems

import euler.EulerProblem
import euler.problems.Problem35.PRIMES_BELOW_ONE_MILLION

object Problem37 : EulerProblem() {
    override val name = "Truncatable Primes"

    private class Prime(val value: Int) {
        constructor(value: String) : this(value.toInt())

        private val digits = value.toString().toList()

        private val truncatedForms by lazy {
            digits.indices.flatMap {
                listOf(
                    digits.slice(0..it),
                    digits.slice(it until digits.size)
                )
            }.map { it.joinToString(separator = "").toBigInteger() }.toSortedSet()
        }

        val isTruncatablePrime by lazy {
            if (value in listOf(2, 3, 5, 7)) false
            else truncatedForms.all { it.isProbablePrime(100) }
        }

        override fun toString() = "$value"
    }

    override fun realProblem(): String = buildString {
        append("Find the sum of the only eleven primes that are both truncatable from left to right and right to left: ")
        val primes = PRIMES_BELOW_ONE_MILLION.map { Problem37.Prime(it.toString()) }

        val truncatablePrimes = primes.filter { it.isTruncatablePrime }
        append(truncatablePrimes.sumOf { it.value })
    }
}

fun main() {
    println(Problem37.solve())
}

