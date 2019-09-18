import java.io.File
import java.math.BigInteger
import kotlin.math.floor
import kotlin.math.sqrt

val THOUSAND_DIGIT_NUMBER = File("res/thousand.txt").readText().map { it.toInt() - 48 }
val TWENTY_SQUARE_GRID = File("res/20x20grid.txt").readLines().map { row -> row.split(' ').map { it.toBigInteger() } }

fun fibonacciUntil(max: Int) = mutableListOf(1, 1)
    .also { while (it.last() < max) it.add(it.takeLast(2).sum()) }

fun primesBelow(max: Int): MutableList<Int> {
    var result = (listOf(2) + (3..max step 2).toList())
    val divisors = (2..sqrt(max.toDouble()).toInt())

    divisors.forEach { divisor ->
        result = result.filterNot { it % divisor == 0 && it != divisor }
            .toMutableList()
    }
    return result.toMutableList()
}

fun primeFactors(input: Int): List<BigInteger> = primeFactors(input.toBigInteger())

fun primeFactors(input: BigInteger): List<BigInteger> {
    if (input == BigInteger.ONE) return listOf(input)

    // may need to increase the starting list of primes... setting a const for now
    // but only checking primes less than input
    var relevantPrimes = primesBelow(10000)
        .filter { it.toBigInteger() <= input }
        .map { it.toBigInteger() }
        .toMutableList()
    val output: MutableList<BigInteger> = mutableListOf()
    var current = input

    // this could cause problems with big primes
    if (relevantPrimes.contains(input)) return listOf(input)

    while (product(output) != input) {
        if (relevantPrimes.isEmpty()) throw Exception("might need more primes")
        while (current % relevantPrimes.first() == BigInteger.ZERO) {
            current /= relevantPrimes.first()
            output.add(relevantPrimes.first())
        }
        relevantPrimes = relevantPrimes.drop(1).toMutableList()
    }

    return output
}

fun product(input: MutableList<BigInteger>?): BigInteger? {
    if (input == mutableListOf<BigInteger>()) return BigInteger.ZERO
    var output = input?.first()
    input?.drop(1)?.forEach { output = (output ?: BigInteger.ZERO).times(it) }
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

fun squareOfSum(input: IntRange) = input.sum().toBigInteger().pow(2)

fun sumOfSquares(input: IntRange): BigInteger {
    var output = BigInteger.ZERO
    input.map { it.toBigInteger().pow(2) }.forEach { output = output.plus(it) }
    return output
}

fun getPrimes(numPrimes: Int): List<Int> {
    var primes = mutableListOf(2)
    var current = 3
    while (primes.size < numPrimes) {
        if (primes.none { current % it == 0 }) primes.add(current)

        // adding 2 to only try odd numbers
        current += 2
    }
    return primes.toList()
}
fun List<Int>.sublistOrNull(fromIndex: Int, toIndex: Int): List<Int>? {
    var output = listOf<Int>()
    try {
        output = this.subList(fromIndex, toIndex)
    } catch (e: Throwable) {
    }
    return output
}

fun largestProductAdjacent(numAdjacent: Int): BigInteger {

    var maxProduct = BigInteger.ZERO
    THOUSAND_DIGIT_NUMBER.forEachIndexed { index, i ->
        product(
            THOUSAND_DIGIT_NUMBER.sublistOrNull(
                index,
                index + numAdjacent
            )?.map { it.toBigInteger() }?.toMutableList()
        )
            .let {
                if (it ?: BigInteger.ZERO > maxProduct) maxProduct = it
            }
    }
    return maxProduct
}

fun sumTo1000(): List<Triple<Int, Int, Int>> {
    val output = mutableListOf<Triple<Int, Int, Int>>()
    for (i in 1 until 999) {
        for (j in i until 999) {
            for (k in j until 999) {
                if (i + j + k == 1000) output.add(Triple(i, j, k))
            }
        }
    }
    return output
}

fun isPythagTriple(input: Triple<Int, Int, Int>) =
    input.first.toBigInteger().pow(2) + input.second.toBigInteger().pow(2) == input.third.toBigInteger().pow(2)

fun List<BigInteger>.sum(): BigInteger {
    var output = BigInteger.ZERO
    this.forEach { output += it }
    return output
}

fun largestGridProduct(grid: List<List<BigInteger>>, length: Int) {

}