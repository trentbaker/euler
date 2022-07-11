package euler.problems

import euler.PRIMES_BELOW_ONE_MILLION
import euler.timed

/**
 * The number 3797 has an interesting property. Being prime itself, it is possible to continuously remove
 *  digits from left to right, and remain prime at each stage: 3797, 797, 97, and 7.
 *  Similarly, we can work from right to left: 3797, 379, 37, and 3.
 *
 * Find the sum of the only eleven primes that are both truncatable from left to right and right to left.
 *
 * NOTE: 2, 3, 5, and 7 are not considered to be truncatable primes.
 */
object Problem37 {

    class Prime(val value: Int) {
        constructor(value: String) : this(value.toInt())

        private val digits = value.toString().toList()

        val truncatedForms by lazy {
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
}

fun main() = timed {
    val primes = PRIMES_BELOW_ONE_MILLION.map { Problem37.Prime(it) }

    val truncatablePrimes = primes.filter { it.isTruncatablePrime }

    println(truncatablePrimes.sumOf { it.value })

}