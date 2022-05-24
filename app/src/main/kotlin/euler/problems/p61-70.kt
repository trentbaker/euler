package euler.problems

import euler.Triangles
import euler.importTriangle
import euler.largestWeightRoute

fun main() {
    println("PE67. Find the maximum total from top to bottom of the big ass triangle: " +
            importTriangle(Triangles.BIG_ASS_TRIANGLE).largestWeightRoute()
    )
}