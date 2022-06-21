package euler.problems

import euler.problems.Problem30.isDigitFifthPower
import euler.problems.Problem30.isDigitFourthPower
import euler.sum
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.math.BigInteger

class Problem30Test : FunSpec({

    test("1634") {
        val input = BigInteger(1634)

        input.isDigitFourthPower() shouldBe true
    }

    test("8208") {
        val input = BigInteger(8208)

        input.isDigitFourthPower() shouldBe true
    }

    test("9474") {
        val input = BigInteger(9474)

        input.isDigitFourthPower() shouldBe true
    }
    test("replicating example") {
        val searchBound = 10000
        val bigIntegerSequence = generateSequence(BigInteger(2)) {
            if (it > BigInteger(searchBound)) null else
                it + BigInteger.ONE
        }
        val digitFifthPowers = bigIntegerSequence.filter { it.isDigitFourthPower() }

        val yeet = digitFifthPowers.toList()
        println(yeet)
        println(yeet.sum())
    }
    test("real deal") {
        val searchBound = 1000000 // shot in dark, was still fast to execute
        val bigIntegerSequence = generateSequence(BigInteger(2)) {
            if (it > BigInteger(searchBound)) null else
                it + BigInteger.ONE
        }
        val digitFifthPowers = bigIntegerSequence.filter { it.isDigitFifthPower() }

        val yeet = digitFifthPowers.toList()
        println(yeet)
        println(yeet.sum())
    }
})
