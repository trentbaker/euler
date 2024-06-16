package euler.problems

import euler.EulerProblem
import java.time.DayOfWeek
import java.time.LocalDate

object Problem19 : EulerProblem() {
    override val name = "Counting Sundays"

    private val twentiethCenturyFirstOfTheMonth = generateSequence(LocalDate.of(1901, 1, 1)) {
        it.plusMonths(1).takeUnless { it.isAfter(LocalDate.of(2000, 12, 31)) }
    }

    override fun realProblem(): String = buildString {
        append("How many Sundays fell on the first of the month during the twentieth century (1 Jan 1901 to 31 Dec 2000): ")
        val sundays = twentiethCenturyFirstOfTheMonth.filter { it.dayOfWeek == DayOfWeek.SUNDAY }
        append(sundays.count())
    }
}

fun main() {
    println(Problem19.solve())
}