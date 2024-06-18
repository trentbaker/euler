package euler.problems

import euler.BigInteger
import euler.EulerProblem
import java.io.File
import java.math.BigInteger

object Problem35 : EulerProblem() {
    override val name = "Circular Primes"

    // pre-compute all primes below 1 million so we can go be lazy
    val PRIMES_BELOW_ONE_MILLION = File("app/src/main/resources/primes_below_one_million.txt")
        .readText()
        .split(",")
        .map { BigInteger(it) }
        .toSortedSet()

    private fun isCircularPrime(input: BigInteger): Boolean {
        val s = input.toString()
        val versions = s.indices.map {
            s.drop(it) + s.take(it)
        }
        return versions.all { BigInteger(it) in PRIMES_BELOW_ONE_MILLION }
    }

    override fun exampleProblem(): String = buildString {
        append("Find the 13 circular primes below 100: ")
        val result = PRIMES_BELOW_ONE_MILLION.takeWhile { it < BigInteger(100) }
        append(result.toList())
    }

    override fun realProblem(): String = buildString {
        append("How many circular primes are there below one million: ")
        val result = PRIMES_BELOW_ONE_MILLION.filter { isCircularPrime(it) }
        append(result.count())
    }
}

fun main() {
    println(Problem35.solve())
}