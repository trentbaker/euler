package euler.problems

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Problem27Test : FunSpec({
    test("eulers formula") {
        val primeQuadratic = Problem27.PrimeQuadratic(1, 41)

        primeQuadratic.consecutivePrimes() shouldBe 40
    }
    test("incredible formula") {
        val primeQuadratic = Problem27.PrimeQuadratic(-79, 1601)

        primeQuadratic.consecutivePrimes() shouldBe 80
    }
})
