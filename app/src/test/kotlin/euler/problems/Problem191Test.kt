package euler.problems

import euler.problems.Problem191.AbsentTrackerState.Companion
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe


class Problem191Test : FunSpec({
    context("brute force") {
        test("simple enumeration") {
            val result = Problem191.enumerateStrings("ABC".toList(), 2).toList()
            val expected = listOf(
                "AA",
                "AB",
                "AC",
                "BA",
                "BB",
                "BC",
                "CA",
                "CB",
                "CC",
            )

            result shouldBe expected
        }
        test("example problem") {
            val possibleStrings = Problem191.enumerateStrings("OLA".toList(), 4)
            val prizeStrings = possibleStrings
                .filterNot { it.contains("AAA") }
                .filterNot { it.count { it == 'L' } >= 2 }
                .onEach { println("$it + A") }

            val result = prizeStrings.count()

            result shouldBe 43
        }
    }
    context("iteration") {
        test("example problem") {
            val result = Problem191.AbsentTrackerState.daySequence.elementAt(3).prizeStrings
            result shouldBe 43
        }
    }
})