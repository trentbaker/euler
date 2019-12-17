import java.math.BigInteger
import java.time.LocalDate
import kotlin.math.floor
import kotlin.math.roundToInt
import kotlin.math.sqrt

fun fibonacciUntil(max: Int): Sequence<Int> = fibonacciSequence().takeWhile { it < max }

fun primesBelow(max: Int): MutableList<Int> {
	var result = (listOf(2) + (3..max step 2).toList())
	val divisors = (2..sqrt(max.toDouble()).toInt())

	divisors.forEach { divisor ->
		result = result.filterNot { it % divisor == 0 && it != divisor }
			.toMutableList()
	}
	return result.toMutableList()
}

fun Int.isPrime() = when (this) {
	1 -> false
	2 -> true
	else -> (listOf(2) + (3 until this step 2).toList()).all { this % it != 0 }
}

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
	var current = input.first.toDouble()
	while (input.any { current % it != 0.0 })
		current += input.filter { current % it == 0.0 }.max()
			?: 1
	return current.toInt()
}

fun squareOfSum(input: IntRange): BigInteger = input.sum().toBigInteger().pow(2)

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

fun isPythagTriple(input: Triple<Int, Int, Int>): Boolean =
	input.first.toBigInteger().pow(2) + input.second.toBigInteger().pow(2) == input.third.toBigInteger().pow(2)

fun List<BigInteger>.sum(): BigInteger {
	var output = BigInteger.ZERO
	this.forEach { output += it }
	return output
}

fun Int.factors(): List<Int> = mutableListOf<Int>().also { output ->
	// can check only numbers < sqrt(this) if you add inferred factors
	IntRange(0, sqrt(this.toDouble()).toInt()).forEach { current ->
		if (current != 0 && this % current == 0) {
			output.add(current) // add the found factor
			output.add(this / current) // add the inferred factor
		}
	}
}.distinct().sorted().toList()

fun countRoutesInSquareGrid(dimension: Int): BigInteger {
	var paths = BigInteger.ONE
	for (i in 0 until dimension) {
		paths *= (2 * dimension).toBigInteger() - i.toBigInteger()
		paths /= (i + 1).toBigInteger()
	}
	return paths
}

fun numberToWords(input: Int): String =
	teenWords[input] ?: input.toString().map { it.toInt() - 48 }.let { num ->
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
				val layerWidth = layerString.length.let { if (it < 0) 0 else it }
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

fun amicableNumbersBelow(max: Int) = IntRange(0, max).map { it to it.factors().dropLast(1).sum() }.let { facs ->
	facs.filter { it.first == facs.getOrNull(it.second)?.second ?: false && it.first != it.second }
}.map { it.first }

fun List<String>.calculateNameScores() = this.sorted().mapIndexed { index, s ->
	s to s.toList().map { it.toInt() - 64 }.sum() * (index + 1)
}

fun Int.isAbundant() = this.factors().dropLast(1).sum() > this

fun List<Int>.cannotSumFromAbundant() = this.filter { it.isAbundant() }.let { abundants ->
	this.filter { current ->
		abundants.map { current - it }.none { abundants.contains(it) }
	}
}

fun List<Pair<List<String>, List<String>>>.importAsHands(): List<Pair<Hand, Hand>> = this.map { Hand(it.first.map { Card(it) }) to Hand(it.second.map { Card(it) }) }

fun String.cycle(count: Int): String = (count % this.length).let { this.drop(it) + this.take(it) }

fun Int.cycle(count: Int): Int = this.toString().cycle(count).toInt()

// can pass a set of primes to calculate faster
// otherwise falls back to brute force
fun String.isCircularPrime(primes: HashSet<String>? = null) = (if (primes != null) {
	this.indices.map { primes.contains(this.cycle(it)) }
} else this.indices.map { this.cycle(it).toInt().isPrime() }).all { it }
