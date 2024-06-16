package euler.problems

import euler.EulerProblem
import kotlin.math.roundToInt

object Problem18 : EulerProblem() {
    override val name = "Maximum Path Sum I"

    private class BinaryNode(var value: Int) : Comparable<BinaryNode> {
        var l: BinaryNode? = null
        var r: BinaryNode? = null

        override fun toString(): String = value.toString()
        override fun compareTo(other: BinaryNode) = value

        fun eatLargestLivingChild() {
            value += listOfNotNull(l, r).maxByOrNull { it.value }?.value ?: 0
        }
    }

    private fun importTriangle(input: String): List<List<BinaryNode>> {
        val lines = input.trimIndent().split("\n").map { it.split(" ").filter { it.isNotBlank() } }
        return lines.map { layer -> layer.map { BinaryNode(it.toInt()) } }.let { tree ->
            tree.forEachIndexed { layerIndex, layer ->
                layer.forEachIndexed { index, node ->
                    node.l = tree.getOrNull(layerIndex + 1)?.getOrNull(index)
                    node.r = tree.getOrNull(layerIndex + 1)?.getOrNull(index + 1)
                }
            }
            tree
        }
    }

    private fun List<List<BinaryNode>>.format() =
        this.flatten().map { it.value.toString().length }.average().roundToInt().let { averageWidth ->

            fun List<BinaryNode>.withPaddedNumbers() =
                this.joinToString(" ") { it.toString().padStart(averageWidth, '0') }

            this.last().withPaddedNumbers().length.let { totalWidth ->
                this.map { layer ->
                    val layerString = layer.withPaddedNumbers()
                    val layerWidth = layerString.length
                    val layerPadding = ((totalWidth - layerWidth) / 2).takeIf { it >= 0 } ?: 0
                    "".padStart(layerPadding) + layerString
                }
            }
        }


    private fun largestWeightRoute(triangle: List<List<BinaryNode>>, print: Boolean = false): Int {
        triangle.asReversed().forEach { layer -> layer.forEach { it.eatLargestLivingChild() } }
        if (print) triangle.format().forEach { println(it) }
        return triangle.first().first().value
    }

    override fun exampleProblem(): String = buildString {
        val triangle = """
           3
          7 4
         2 4 6
        8 5 9 3
        """

        append("Find the maximum total from top to bottom of the triangle: ")
        val result = largestWeightRoute(importTriangle(triangle))
        append(result)
    }

    override fun realProblem(): String = buildString {
        val triangle = """
                      75
                     95 64
                    17 47 82
                   18 35 87 10
                  20 04 82 47 65
                 19 01 23 75 03 34
                88 02 77 73 07 63 67
               99 65 04 28 06 16 70 92
              41 41 26 56 83 40 80 70 33
             41 48 72 33 47 32 37 16 94 29
            53 71 44 65 25 43 91 52 97 51 14
           70 11 33 28 77 73 17 78 39 68 17 57
          91 71 52 38 17 14 91 43 58 50 27 29 48
         63 66 04 68 89 53 67 30 73 16 69 87 40 31
        04 62 98 27 23 09 70 98 73 93 38 53 60 04 23
        """

        append("Find the maximum total from top to bottom of the triangle: ")
        val result = largestWeightRoute(importTriangle(triangle))
        append(result)
    }
}


fun main() {
    println(Problem18.solve())
}