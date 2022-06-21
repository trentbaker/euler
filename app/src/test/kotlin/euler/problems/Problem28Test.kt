package euler.problems

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Problem28Test : FunSpec({
    test("5 by 5") {
        val spiral = Problem28.NumberSpiral(5)

        with(spiral.diagonalNumbers){
            this shouldBe listOf(1, 3, 5, 7, 9, 13, 17, 21, 25)
            this.sum() shouldBe 101
        }
    }
    test("7 by 7") {
        val spiral = Problem28.NumberSpiral(7)

        with(spiral.diagonalNumbers){
            this shouldBe listOf(1, 3, 5, 7, 9, 13, 17, 21, 25, 31, 37, 43, 49)
            this.sum() shouldBe 261
        }
    }
    test("1001 by 1001") {
        val spiral = Problem28.NumberSpiral(1001)

        with(spiral.diagonalNumbers){
            this.sum() shouldBe 669171001
        }
    }
})
