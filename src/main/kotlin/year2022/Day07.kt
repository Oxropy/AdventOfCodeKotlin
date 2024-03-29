package year2022

import AoCApp

object Day07 : AoCApp() {
    @JvmStatic
    fun main(args: Array<String>) {
        val input = inputLines
        printPart(1, part1(input))
        printPart(2, part2(input))
    }

    private fun part1(input: List<String>): String {
        return calculateDirectorySizes(input).values.filter { it <= 100_000 }.sum().toString()
    }

    private fun part2(input: List<String>): String {
        val sizes = calculateDirectorySizes(input)
        val usedSpace = sizes.getValue("/")
        val availableSpace = 70_000_000 - usedSpace
        return sizes.values.filter { availableSpace + it >= 30_000_000 }.minOf { it }.toString()
    }

    private fun calculateDirectorySizes(inputLines: List<String>): Map<String, Long> {
        val sizes = mutableMapOf<String, Long>()
        val currentDir = mutableListOf<String>()
        for (line in inputLines) {
            when {
                line.startsWith("$ cd") -> {
                    when (val ref = line.substring(5)) {
                        ".." -> {
                            currentDir.removeLast()
                        }
                        "/" -> {
                            currentDir.clear()
                        }
                        else -> {
                            currentDir.add(ref)
                        }
                    }
                }
                line == "$ ls" || line.startsWith("dir ") -> {
                    // nothing to do here
                }
                else -> {
                    val (sizeString) = line.split(' ', limit = 2)
                    val size = sizeString.toLong()
                    for (index in 0..currentDir.size) {
                        val path = currentDir.asSequence().take(index).joinToString(separator = "/")
                        sizes.compute(path) { _, pathSize -> (pathSize ?: 0) + size }
                    }
                }
            }
        }

        return sizes
    }
}


