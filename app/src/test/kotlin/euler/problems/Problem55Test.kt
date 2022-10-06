package euler.problems

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Problem55Test : FunSpec({

    context("palindromic") {
        test("single digit") {
            Problem55.palindromic(BigInteger(1)) shouldBe true
        }
        test("double digit palindrome") {
            Problem55.palindromic(BigInteger(11)) shouldBe true
        }
        test("double digit non-palindrome") {
            Problem55.palindromic(BigInteger(10)) shouldBe false
        }
        test("triple digit palindrome") {
            Problem55.palindromic(BigInteger(101)) shouldBe true
        }
        test("triple digit non-palindrome") {
            Problem55.palindromic(BigInteger(102)) shouldBe false
        }
    }
})
