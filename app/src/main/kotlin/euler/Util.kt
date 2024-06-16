package euler

import euler.objects.Sudoku
import euler.problems.Problem1
import euler.problems.Problem10
import euler.problems.Problem11
import euler.problems.Problem12
import euler.problems.Problem13
import euler.problems.Problem14
import euler.problems.Problem15
import euler.problems.Problem16
import euler.problems.Problem17
import euler.problems.Problem18
import euler.problems.Problem19
import euler.problems.Problem2
import euler.problems.Problem20
import euler.problems.Problem3
import euler.problems.Problem4
import euler.problems.Problem5
import euler.problems.Problem6
import euler.problems.Problem7
import euler.problems.Problem8
import euler.problems.Problem9
import java.io.File

fun <T> timed(name: String? = null, fn: () -> T): T = System.currentTimeMillis().let { start ->
    fn().also {
        println(
            buildString {
                if (name != null) append("$name ")
                append("took ${System.currentTimeMillis() - start}ms")
            }
        )
    }
}

fun String.swap(a: Int, b: Int) = this.let { original ->
    toMutableList().apply {
        set(a, original[b])
        set(b, original[a])
    }.joinToString("")
}

fun File.importSudokus() = readLines().chunked(10).map {
    val importedBoard = it.drop(1).map {
        it.mapNotNull {
            when (it) {
                ' ' -> null
                '0' -> ' '
                else -> it
            }
        }
    }
    Sudoku(importedBoard, it.first())
}

abstract class EulerProblem {
    open fun exampleProblem(): String = ""
    open fun realProblem(): String = ""
    open val name: String = this::class.simpleName ?: "???"
    fun solve(): String {
        val startTime = System.currentTimeMillis()
        return buildString {
            appendLine("----- $name -----")
            exampleProblem().let { solution ->
                if (solution.isNotBlank()) appendLine("Example: $solution")
            }
            realProblem().let { solution ->
                if (solution.isNotBlank()) appendLine("Real: $solution")
            }
            appendLine("----- ${System.currentTimeMillis() - startTime}ms -----")
        }
    }
}


fun main() {
    val problems = listOf(
        Problem1,
        Problem2,
        Problem3,
        Problem4,
        Problem5,
        Problem6,
        Problem7,
        Problem8,
        Problem9,
        Problem10,
        Problem11,
        Problem12,
        Problem13,
        Problem14,
        Problem15,
        Problem16,
        Problem17,
        Problem18,
        Problem19,
        Problem20,
    ).onEach { println(it.solve()) }
}