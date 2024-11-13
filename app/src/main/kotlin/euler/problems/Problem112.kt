package euler.problems

import euler.EulerProblem
import java.math.BigDecimal
import java.math.RoundingMode

object Problem112 : EulerProblem() {
    override val name = "Bouncy Numbers"

    private data class State(
        val current: Int = 1,
        val bouncyCount: Int = 0,
        val nonBouncyCount: Int = 1,
    ) {
        val isBouncy: Boolean
            get() {
                val digits = current.toString().toList()
                val ascending = digits.windowed(2).all { it.first() >= it.last() }
                val descending = digits.windowed(2).all { it.first() <= it.last() }

                return !ascending && !descending
            }

        val proportion: BigDecimal = bouncyCount.toBigDecimal().divide(current.toBigDecimal(), 20, RoundingMode.DOWN)
    }

    private fun isBouncy(value: Int): Boolean {
        val digits = value.toString().toList()
        val ascending = digits.windowed(2).all { it.first() >= it.last() }
        val descending = digits.windowed(2).all { it.first() <= it.last() }

        return !ascending && !descending
    }

    private val bouncyCounter = generateSequence(State()) { oldState ->
        oldState.copy(current = oldState.current + 1).run {
            if (isBouncy) copy(bouncyCount = bouncyCount + 1)
            else copy(nonBouncyCount = nonBouncyCount + 1)
        }
    }

    override fun exampleProblem(): String = buildString {
        append("Clearly there cannot be any bouncy numbers below one-hundred: ")
        appendLine(bouncyCounter.take(100).last().bouncyCount)

        append("There are 525 bouncy numbers below 1000: ")
        appendLine(bouncyCounter.take(1000).last().bouncyCount)

        append("The least number for which the proportion of bouncy numbers first reaches 50% is 538: ")
        appendLine(bouncyCounter.takeWhile { it.proportion <= BigDecimal("0.5") }.last().current)

        append("By the time we reach 21780, the proportion of bouncy numbers is 90%: ")
        appendLine(bouncyCounter.take(21780).last().proportion)
    }

    override fun realProblem(): String = buildString {
        append("Find the least number for which the proportion of bouncy numbers is exactly 99%: ")
        appendLine(bouncyCounter.takeWhile { it.proportion <= BigDecimal("0.99") }.last().current)
    }
}

fun main() {
    println(Problem112.solve())
}