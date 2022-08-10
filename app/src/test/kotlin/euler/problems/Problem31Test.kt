package euler.problems

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Problem31Test : FunSpec({
    test("simple") {
        val waysCount = Problem31.coinSums(
            5,
            listOf(1, 2)
        )
        waysCount shouldBe 3
    }
    test("actual") {
        val waysCount = Problem31.coinSums(
            200,
            listOf(1, 2, 5, 10, 20, 50, 100, 200)
        )

        waysCount shouldBe 73682
    }
})
