package euler.problems

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class Problem29Test : FunSpec({
    // How many distinct terms are in the sequence generated by ab for 2 ≤ a ≤ 100 and 2 ≤ b ≤ 100?
    test("sample data") {
        val input = Problem29.setOfPowers(
            aRange = 2..5,
            bRange = 2..5,
        )

        val result = input.distinct().sorted()

        result shouldHaveSize 15
        result shouldBe listOf(4, 8, 9, 16, 25, 27, 32, 64, 81, 125, 243, 256, 625, 1024, 3125).map { BigInteger(it) }
    }

    test("real deal") {
        val input = Problem29.setOfPowers(
            aRange = 2..100,
            bRange = 2..100,
        )

        val result = input.distinct().sorted()

        result shouldHaveSize 9183
    }
})