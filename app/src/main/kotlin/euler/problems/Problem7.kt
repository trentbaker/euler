package euler.problems

import euler.EulerProblem

object Problem7 : EulerProblem() {
    override val name = "10001st Prime"

    // just do trial division it's easy
    private fun getPrimes(numPrimes: Int): List<Int> {
        val primes = mutableListOf(2)
        var current = 3
        while (primes.size < numPrimes) {
            if (primes.none { current % it == 0 }) primes.add(current)

            // adding 2 to only try odd numbers
            current += 2
        }
        return primes.toList()
    }

    override fun exampleProblem(): String = buildString {
        append("Find the 6th prime number: ")
        val primes = getPrimes(6)
        append(primes[5]) // index 5 is the sixth prime
    }

    override fun realProblem(): String = buildString {
        append("Find the 6th prime number: ")
        val primes = getPrimes(10001)
        append(primes[10000]) // index 10000 is the 10001th prime
    }
}

fun main() {
    println(Problem7.solve())
}