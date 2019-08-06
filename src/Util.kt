import java.math.BigInteger
import kotlin.math.floor
import kotlin.math.sqrt

fun fibonacciUntil(max: Int) = mutableListOf(1, 1)
    .also { while (it.last() < max) it.add(it.takeLast(2).sum()) }

fun primesBelow(max: Int): MutableList<Int> {
    var result = (2..max).toMutableList()
    val divisors = (2..sqrt(max.toDouble()).toInt())

    divisors.forEach { divisor ->
        result = result.filterNot { it % divisor == 0 && it != divisor }
            .toMutableList()
    }

    return result
}

fun primeFactors(input: BigInteger): List<Int> {
    // may need to increase the starting list of primes... setting a const for now
    var relevantPrimes = primesBelow(10000)
        .filterNot { it.toBigInteger() == input }
        .toMutableList()
    val output: MutableList<Int> = mutableListOf()
    var current = input

    while (product(output) != input) {
        if (relevantPrimes.isEmpty()) throw Exception("probably need more primes")
        while (current % relevantPrimes.first().toBigInteger() == BigInteger.ZERO) {
            current /= relevantPrimes.first().toBigInteger()
            output.add(relevantPrimes.first())
        }
        relevantPrimes = relevantPrimes.drop(1).toMutableList()
    }

    return output
}

fun product(input: MutableList<Int>): BigInteger {
    if (input.isEmpty()) return BigInteger.ZERO
    var output = input.first().toBigInteger()
    input.drop(1).forEach { output *= it.toBigInteger() }
    return output
}

fun Int.isPalindromic(): Boolean {
    var half = floor(this.toString().length / 2.0).toInt()

    val firstHalf = this.toString().substring(0, half)
    if (this.toString().length % 2 != 0) half++
    val secondHalf = this.toString().substring(half).reversed()

    return firstHalf == secondHalf
}

fun largestPalindromeProduct3Digits(): Int {
    val output = mutableListOf<Int>()
    for (x in 100 until 999) {
        for (y in 100 until 999) {
            with(x * y) {
                if (this.isPalindromic()) output.add(this)
            }
        }
    }
    return output.max() ?: throw Exception("found no palindromes")
}

fun smallestDivisbleBy(input: IntRange): Int {
    var current = 2520.0
    while (input.any { current % it != 0.0 })
        current += input.filter { current % it == 0.0 }.max()
            ?: 1
    return current.toInt()
}