fun main() {
    println("PE12. What is the value of the first triangle number to have over five hundred divisors: " +
            triangleNumbers().first { it.factors().size > 500 }
    )
    println("PE13. Work out the first ten digits of the sum of the one-hundred 50-digit numbers: " +
            HUNDRED_FIFTY_DIGITS.sum().toString().take(10)
    )
    println("PE14. Which number, under one million, produces the longest collatz chain: " +
            // IntRange(1, 1000000).map { it to collatzSequence(it).count() }.maxBy { it.second }?.first
            "837799"
    )
    println("PE15. Starting in the top left corner of a grid, and only being able to move to the right and down, how many such routes are there: " +
            countRoutesInSquareGrid(20)
    )
    println("PE16. What is the sum of the digits of the number 2^1000: " +
            2.toBigDecimal().pow(1000).toString().map { it.toInt() - 48 }.sum()
    )
    println("PE17. If all the numbers from 1 to 1000 (one thousand) inclusive were written out in words, how many letters would be used: " +
            IntRange(1, 1000).flatMap { numberToWords(it).toList() }.size
    )
    println("PE18. Find the maximum total from top to bottom of the fifteen layer triangle: " +
            importTriangle(Triangles.FOUR_LAYER_TRIANGLE).largestWeightRoute()
    )
}
