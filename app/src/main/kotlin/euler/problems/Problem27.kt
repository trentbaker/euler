package euler.problems

import euler.BigInteger
import euler.EulerProblem
import java.math.BigInteger


object Problem27 : EulerProblem() {
    override val name = "Quadratic Primes"

    class PrimeQuadratic(private val a: BigInteger, private val b: BigInteger, private val certainty: Int = 2) {
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

    private fun consideredQuadratics(aMagnitude: Int, bMagnitude: Int) = (-aMagnitude..aMagnitude).flatMap { a ->
        (-bMagnitude..bMagnitude).map { b ->
            PrimeQuadratic(a, b)
        }
    }

    override fun realProblem(): String = buildString {
        append("Find the products of the coefficients of the quadratic expression that produces the most primes for consecutive values of n: ")
        val considered = consideredQuadratics(999, 1000)
        val result = considered.maxBy { it.consecutivePrimes() }
        append(result.productOfCoefficients)
    }
}

fun main() {
    println(Problem27.solve())
}