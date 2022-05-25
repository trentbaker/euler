package euler.problems

import euler.problems.Problem26.detectCycle
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
import kotlin.system.measureTimeMillis

class Problem26KtTest : FunSpec({
    context("finding cycles") {
        withData(
            listOf(
                "" to null,
                "5" to null,
                "33333333333333333333" to "3",
                "25" to null,
                "2" to null,
                "16666666666666666666" to "6",
                "14285714285714285714" to "142857",
                "125" to null,
                "11111111111111111111" to "1",
                "1" to null,
                "09090909090909090909" to "09",
                "05263157894736842105" to null,
                "00221729490022172949" to "0022172949",
                "1001221100123001221100123" to "001221100123",
                "12001100200011002000110020" to "00110020",
            ),
        ) {
            measureTimeMillis {
                val (input, expected) = it
                detectCycle(input) shouldBe expected
            }.also { println("took ${it}ms") }
        }
    }
    test("single case") {
        val input = "12001100200011002000110020"
        val expected: String? = "00110020"

        detectCycle(input) shouldBe expected
    }
})
