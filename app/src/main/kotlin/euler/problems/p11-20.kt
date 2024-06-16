package euler.problems

import euler.Triangles
import euler.factorial
import euler.firstOfTheMonthsUntil
import euler.importTriangle
import euler.largestWeightRoute
import euler.numberToWords
import java.time.DayOfWeek
import java.time.LocalDate

fun main() {
    println(
        "PE16. What is the euler.sum of the digits of the number 2^1000: " +
                2.toBigDecimal().pow(1000).toString().map { it.code - 48 }.sum()
    )
    println(
        "PE17. If all the numbers from 1 to 1000 (one thousand) inclusive were written out in words, how many letters would be used: " +
                IntRange(1, 1000).flatMap { numberToWords(it).toList() }.size
    )
    println(
        "PE18. Find the maximum total from top to bottom of the fifteen layer triangle: " +
                importTriangle(Triangles.FIFTEEN_LAYER_TRIANGLE).largestWeightRoute()
    )
    println(
        "PE19. How many Sundays fell on the first of the month during the twentieth century (1 Jan 1901 to 31 Dec 2000): " +
                LocalDate.of(1901, 1, 1)
                    .firstOfTheMonthsUntil(LocalDate.of(2000, 12, 31))
                    .filter { it.dayOfWeek == DayOfWeek.SUNDAY }.size
    )
    println(
        "PE20. Find the euler.sum of the digits in the number 100!: " +
                100.toBigInteger().factorial().toString().map { it.code - 48 }.sum()
    )
}
