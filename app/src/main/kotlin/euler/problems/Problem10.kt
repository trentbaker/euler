package euler.problems

import euler.EulerProblem

object Problem10: EulerProblem() {

    override fun exampleProblem(): String = buildString {
        append("Find the sum of all the primes below ten: ")
        val primes = Problem3.primesBelow(10)
        append(primes.sumOf { it })
    }

    override fun realProblem(): String = buildString {
        append("Find the sum of all the primes below two million: ")
        val primes = Problem3.primesBelow(2000000)
        append(primes.sumOf { it })
    }
}

fun main(){
    println(Problem10.solve())
}