package problems

import HUNDRED_FIFTY_DIGITS
import TWENTY_SQUARE_GRID
import Triangles
import countRoutesInSquareGrid
import factorial
import factors
import firstOfTheMonthsUntil
import importTriangle
import largestProductOfAdjacent
import largestWeightRoute
import numberToWords
import sum
import triangleNumbers
import java.time.DayOfWeek
import java.time.LocalDate

fun main() {
    println("PE11. What is the greatest product of four adjacent numbers in the same direction (up, down, left, right, or diagonally) in the 20×20 grid: " +
            TWENTY_SQUARE_GRID.largestProductOfAdjacent(4)
    )
    println("PE12. What is the value of the first triangle number to have over five hundred divisors: " +
            triangleNumbers().first { it.factors().size > 500 }
    )
    println("PE13. Work out the first ten digits of the sum of the one-hundred 50-digit numbers: " +
            HUNDRED_FIFTY_DIGITS.sum().toString().take(10)
    )
    println("PE14. Which number, under one million, produces the longest collatz chain: " +
            // IntRange(1, 1000000).map { it to main.collatzSequence(it).count() }.maxBy { it.second }?.first // took 14.899s
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
            importTriangle(Triangles.FIFTEEN_LAYER_TRIANGLE).largestWeightRoute()
    )
    println("PE19. How many Sundays fell on the first of the month during the twentieth century (1 Jan 1901 to 31 Dec 2000): " +
            LocalDate.of(1901, 1, 1)
                    .firstOfTheMonthsUntil(LocalDate.of(2000, 12, 31))
                    .filter { it.dayOfWeek == DayOfWeek.SUNDAY }.size
    )
    println("PE20. Find the sum of the digits in the number 100!: " +
            100.toBigInteger().factorial().toString().map { it.toInt() - 48 }.sum()
    )
}
