fun timed(fn: () -> Any) = System.currentTimeMillis().also { start ->
	fn().also { println("took ${System.currentTimeMillis() - start}ms") }
}

val singleDigitWords = mapOf(
	1 to "ONE",
	2 to "TWO",
	3 to "THREE",
	4 to "FOUR",
	5 to "FIVE",
	6 to "SIX",
	7 to "SEVEN",
	8 to "EIGHT",
	9 to "NINE"
)

val teenWords = mapOf(
	10 to "TEN",
	11 to "ELEVEN",
	12 to "TWELVE",
	13 to "THIRTEEN",
	14 to "FOURTEEN",
	15 to "FIFTEEN",
	16 to "SIXTEEN",
	17 to "SEVENTEEN",
	18 to "EIGHTEEN",
	19 to "NINETEEN"
)

val tenMultipleWords = mapOf(
	2 to "TWENTY",
	3 to "THIRTY",
	4 to "FORTY",
	5 to "FIFTY",
	6 to "SIXTY",
	7 to "SEVENTY",
	8 to "EIGHTY",
	9 to "NINETY"
)

val hundredMultipleWords = mapOf(
	100 to "HUNDRED",
	1000 to "THOUSAND"
)
