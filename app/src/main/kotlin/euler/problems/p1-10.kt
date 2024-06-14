package euler.problems

import euler.getPrimes
import euler.isPythagTriple
import euler.largestProductAdjacent
import euler.product
import euler.squareOfSum
import euler.sumOfSquares
import euler.sumTo1000

fun main() {
    println("PE6. Find the difference between the euler.sum of the squares of the first one hundred natural numbers and the square of the euler.sum: " +
            squareOfSum(1..100).minus(sumOfSquares(1..100))
    )
    println("PE7. What is the 10001st prime number: " +
            getPrimes(10001).last()
    )
    println("PE8. Find the thirteen adjacent digits in the 1000-digit number that have the greatest Product. What is the value of this Product: " +
            largestProductAdjacent(13)
    )
    println("PE9. There exists exactly one Pythagorean triplet for which a + b + c = 1000. Find the Product abc: " +
            product(sumTo1000().first { isPythagTriple(it) }.toList().toMutableList().map { it.toBigInteger() }.toMutableList())
    )
    println("PE10. Find the euler.sum of all the primes below two million: " +
            // main.euler.primesBelow(2000000).map { it.toBigInteger() }.euler.sum() // took 13.194s
            "142913828922"
    )
}

