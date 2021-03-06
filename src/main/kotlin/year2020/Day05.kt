package year2020

import AoCApp

object Day05: AoCApp() {
    @JvmStatic
    fun main(args: Array<String>) {
        printPart(1, part1(inputLines))
        printPart(2, part2(inputLines))
    }

    private fun part1(lines: List<String>): String {
       return lines.maxOf { calcSeatId(it) }.toString()
    }

    private fun part2(lines: List<String>): String {
        return (lines.map { calcSeatId(it) }.sorted().windowed(size = 2, step = 1).first { it[0] + 1 != it[1] }[0] + 1).toString()
    }

    private fun calcSeatId(input: String): Int {
        return calcRow(input.take(7)) * 8 + calcColumn(input.takeLast(3))
    }

    private fun calcRow(input: String): Int {
        return binaryStringToDecimal(input, 'B', 'F')
    }

    private fun calcColumn(input: String): Int {
        return binaryStringToDecimal(input, 'R', 'L')
    }

    private fun binaryStringToDecimal(input: String, upper: Char, lower: Char): Int {
        return Integer.parseInt(String(input.map {
            when (it) {
                upper -> '1'
                lower -> '0'
                else -> unreachable()
            } }.toCharArray()), 2)
    }
}