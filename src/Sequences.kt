import java.math.BigInteger

fun triangleNumbers(start: Int = 1) = generateSequence(Pair(start, triangleNumber(start)), { Pair(it.first + 1, it.second + it.first + 1) })
	.map { it.second }

fun triangleNumber(input: Int) = IntRange(0, input).sum()

fun collatzSequence(start: Int) = collatzSequence(start.toBigInteger())

fun collatzSequence(start: BigInteger) = generateSequence(start) {
	if (it == BigInteger.ONE) null else {
		if (it.mod(BigInteger.TWO)== BigInteger.ZERO) {
			it.div(BigInteger.TWO)
		} else (it.times(3.toBigInteger())) + BigInteger.ONE
	}
}

fun fibonacciSequence(start: Pair<Int, Int> = Pair(0, 1)) = generateSequence(start) { Pair(it.second, it.first + it.second)}.map { it.second }