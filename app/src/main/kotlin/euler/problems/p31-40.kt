package euler.problems

import euler.PRIMES_BELOW_ONE_MILLION
import euler.digitFactorialLoopFlags
import euler.isCircularPrime

fun main() {
    println("PE34. Find the sum of all numbers which are equal to the sum of the factorial of their digits: " +
            digitFactorialLoopFlags.filter { it.second.isEmpty() && it.first > 3.toBigInteger() }.fold(0) { acc, pair -> acc + pair.first.toInt() }
    )
    println("PE34. How many circular primes are there below one million: " +
            PRIMES_BELOW_ONE_MILLION.let { primes ->
                primes.count { it.isCircularPrime(primes) }
            }
    )
}