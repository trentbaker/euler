fun main() {
	println("PE12. What is the value of the first triangle number to have over five hundred divisors: " +
		triangleNumbers().first { it.factors().size > 500 }
	)
	println("PE13. Work out the first ten digits of the sum of the one-hundred 50-digit numbers: " +
		HUNDRED_FIFTY_DIGITS.sum().toString().take(10)
	)
	timed {
		println("PE14. Which number, under one million, produces the longest collatz chain: " +
			IntRange(1, 1000000).map { it to collatzSequence(it).count() }.maxBy { it.second }?.first
		)
	}
}
