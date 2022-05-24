package euler.objects

import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode

class Fraction(
    private val numerator: BigDecimal,
    private val denominator: BigDecimal,
    precision: Int = 200
) {
    constructor(numerator: Int, denominator: Int, precision: Int = 200) : this(
        numerator.toBigDecimal(),
        denominator.toBigDecimal(),
        precision
    )

    private val mc = MathContext(precision, RoundingMode.DOWN)

    override fun toString() = "($numerator/$denominator)"

    val decimal =  numerator.divide(denominator, mc).toString()

    val tail = decimal.drop(2)

    val tails = tail.indices
        .flatMap { listOf(tail.take(it), tail[it].toString()) }
        .filter { it.isNotEmpty() }
        .distinct()

    val cycle = tail.indices.drop(1).map { tail.chunked(it ).distinct() }

    fun findCycle() {
        var currentIndex = 0

    }
}