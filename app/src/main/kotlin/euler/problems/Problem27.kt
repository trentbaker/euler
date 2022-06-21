package euler.problems

import euler.timed
import java.math.BigInteger

fun BigInteger(input: Int): BigInteger = BigInteger.valueOf(input.toLong())

fun main() = timed {
    val possible = Problem27.consideredQuadratics(999, 1000)

    val results = possible.associateWith { primeQuadratic ->
        primeQuadratic.consecutivePrimes().also { println("$primeQuadratic had $it consecutive primes") }
    }

    val (most, consecutivePrimes) = requireNotNull(results.maxByOrNull { it.value })

    println("$most had the most primes with $consecutivePrimes")
    println("the product of the coefficients is ${most.productOfCoefficients}")
}

object Problem27 {
    class PrimeQuadratic(private val a: BigInteger, private val b: BigInteger, private val certainty: Int = 100) {
        constructor(a: Int, b: Int, certainty: Int = 100) : this(BigInteger(a), BigInteger(b), certainty)

        private val fn = { n: BigInteger -> n.pow(2) + a.times(n) + b }
        private val primeSequence = generateSequence(BigInteger.ZERO) { n ->
            if (fn(n).isProbablePrime(certainty)) n + BigInteger.ONE
            else null
        }

        fun consecutivePrimes() = primeSequence.toList().count() - 1

        val productOfCoefficients by lazy { a.times(b) }

        override fun toString() = "(a: $a, b: $b)"
    }

    fun consideredQuadratics(aMagnitude: Int, bMagnitude: Int) = (-aMagnitude..aMagnitude).flatMap { a ->
        (-bMagnitude..bMagnitude).map { b ->
            PrimeQuadratic(a, b)
        }
    }
}