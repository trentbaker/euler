package euler

import euler.objects.Card
import euler.objects.Hand
import euler.problems.Problem12.factorsOf
import java.math.BigInteger
import java.time.LocalDate

fun Int.isPrime() = when (this) {
    1 -> false
    2 -> true
    else -> (listOf(2) + (3 until this step 2).toList()).all { this % it != 0 }
}

@JvmName("mutableNullableBigIntProduct")
fun product(input: List<BigInteger>?): BigInteger =
    input?.takeIf { it.isNotEmpty() }?.toList()?.let { product(it) } ?: BigInteger.ZERO

fun product(input: List<BigInteger>): BigInteger = input.drop(1).let {
    if (input.isEmpty()) return@let BigInteger.ZERO
    var output = input.first()
    it.forEach { output *= it }
    output
}

fun List<Int>.sublistOrNull(fromIndex: Int, toIndex: Int): List<Int> {
    var output = listOf<Int>()
    try {
        output = this.subList(fromIndex, toIndex)
    } catch (_: Throwable) {
    }
    return output
}

fun digitFactorialChainUntilNotUnique(start: Int) = digitFactorialChainUntilNotUnique(start.toBigInteger())

fun digitFactorialChainUntilNotUnique(start: BigInteger): List<BigInteger> {
    var current = start
    val chain = mutableListOf(current)
    do {
        current = digitFactorialChain(current).first()
        chain.add(current)
    } while (!digitFactorialLoopFlags.map { it.first }.contains(current))
    return chain.toList() + (digitFactorialLoopFlags.firstOrNull { it.first == chain.last() }?.second ?: listOf())
}

fun LocalDate.firstOfTheMonthsUntil(end: LocalDate) = mutableListOf<LocalDate>().let {
    var current = this
    while (current.isBefore(end)) {
        it.add(current)
        current = current.plusMonths(1)
    }
    it
}

fun List<Pair<List<String>, List<String>>>.importAsHands(): List<Pair<Hand, Hand>> =
    this.map { Hand(it.first.map { Card(it) }) to Hand(it.second.map { Card(it) }) }



fun String.cycle(count: Int): String = (count % this.length).let { this.drop(it) + this.take(it) }

fun Int.cycle(count: Int): Int = this.toString().cycle(count).toInt()

// can pass a set of primes to calculate faster
// otherwise falls back to brute force
fun String.isCircularPrime(primes: HashSet<String>? = null) = (if (primes != null) {
    this.indices.map { primes.contains(this.cycle(it)) }
} else this.indices.map { this.cycle(it).toInt().isPrime() }).all { it }
