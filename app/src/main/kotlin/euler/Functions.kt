package euler

import euler.objects.BinaryNode
import euler.objects.Card
import euler.objects.Hand
import euler.problems.Problem12.factorsOf
import java.math.BigInteger
import java.time.LocalDate
import kotlin.math.roundToInt
import kotlin.math.sqrt

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

fun numberToWords(input: Int): String =
    teenWords[input] ?: input.toString().map { it.code - 48 }.let { num ->
        when (num.size) {
            1 -> singleDigitWords[num[0]].toString()
            2 -> tenMultipleWords[num[0]] + singleDigitWords.getOrElse(num[1]) { "" }
            3 -> {
                singleDigitWords[num[0]] +
                        "HUNDRED" +
                        (if (tenMultipleWords[num[1]] != null || singleDigitWords[num[2]] != null || teenWords["${num[1]}${num[2]}".toInt()] != null) "AND" else "") +
                        teenWords.getOrElse("${num[1]}${num[2]}".toInt()) {
                            tenMultipleWords.getOrElse(num[1]) { "" } +
                                    singleDigitWords.getOrElse(num[2]) { "" }
                        }
            }

            4 -> {
                singleDigitWords[num[0]] +
                        "THOUSAND" +
                        singleDigitWords.getOrElse(num[1]) { "" } +
                        (if (singleDigitWords[num[1]] != null) "HUNDRED" else "") +
                        (if (tenMultipleWords[num[2]] != null || singleDigitWords[num[3]] != null || teenWords["${num[2]}${num[3]}".toInt()] != null) "AND" else "") +
                        teenWords.getOrElse("${num[2]}${num[3]}".toInt()) {
                            tenMultipleWords.getOrElse(num[2]) { "" } +
                                    singleDigitWords.getOrElse(num[3]) { "" }
                        }
            }

            else -> "oof"
        }
    }

fun importTriangle(input: List<List<String>>): BinaryTree =
    input.map { layer -> layer.map { BinaryNode(it.toInt()) } }.let { tree ->
        tree.forEachIndexed { layerIndex, layer ->
            layer.forEachIndexed { index, node ->
                node.l = tree.getOrNull(layerIndex + 1)?.getOrNull(index)
                node.r = tree.getOrNull(layerIndex + 1)?.getOrNull(index + 1)
            }
        }
        tree
    }

fun BinaryTree.format() =
    this.flatten().map { it.value.toString().length }.average().roundToInt().let { averageWidth ->

        fun List<BinaryNode>.withPaddedNumbers() = this.joinToString(" ") { it.toString().padStart(averageWidth, '0') }

        this.last().withPaddedNumbers().length.let { totalWidth ->
            this.map { layer ->
                val layerString = layer.withPaddedNumbers()
                val layerWidth = layerString.length
                val layerPadding = ((totalWidth - layerWidth) / 2).takeIf { it >= 0 } ?: 0
                "".padStart(layerPadding) + layerString
            }
        }
    }


fun BinaryTree.largestWeightRoute(print: Boolean = false): Int {
    this.asReversed().forEach { layer -> layer.forEach { it.eatLargestLivingChild() } }
    if (print) this.format().forEach { println(it) }
    return this.first().first().value
}

fun Int.factorial() = this.toBigInteger().factorial().toInt()

fun BigInteger.factorial(): BigInteger {
    var out = BigInteger.ONE
    var current = this
    while (current > BigInteger.ZERO) out *= current--
    return out
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

fun amicableNumbersBelow(max: Int) = IntRange(0, max).map { it to factorsOf(it).dropLast(1).sum() }.let { facs ->
    facs.filter { it.first == facs.getOrNull(it.second)?.second ?: false && it.first != it.second }
}.map { it.first }

fun List<String>.calculateNameScores() = this.sorted().mapIndexed { index, s ->
    s to s.toList().map { it.code - 64 }.sum() * (index + 1)
}

fun Int.isAbundant() = factorsOf(this).dropLast(1).sum() > this

fun List<Int>.cannotSumFromAbundant() = this.filter { it.isAbundant() }.let { abundants ->
    this.filter { current ->
        abundants.map { current - it }.none { abundants.contains(it) }
    }
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
