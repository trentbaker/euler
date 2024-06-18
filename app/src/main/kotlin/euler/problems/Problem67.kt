package euler.problems

import euler.EulerProblem
import java.io.File

object Problem67 : EulerProblem() {
    override val name = "Maximum Path Sum II"

    private val bigAssTriangle = File("app/src/main/resources/0067_triangle.txt").readText()

    override fun realProblem(): String = buildString {
        append("Find the maximum total from top to bottom of the triangle: ")
        val result = Problem18.largestWeightRoute(Problem18.importTriangle(bigAssTriangle))
        append(result)
    }
}

fun main() {
    println(Problem67.solve())
}