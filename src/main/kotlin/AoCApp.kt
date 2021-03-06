import java.io.IOException

abstract class AoCApp {

    val input: String
        get() = javaClass.getResourceAsStream("${javaClass.simpleName}.txt")?.readBytes()?.let { String(it, Charsets.UTF_8) } ?: throw IOException("File not found!")

    val inputLines: List<String>
        get() = input.split("\n").map { it.trim() }

    val inputGroupedLines: List<List<String>>
        get() = input.split("\r\n\r\n").map { group -> group.split("\n").map { it.trim() } }

    fun printPart(part: Int, result: String) {
        println("Part $part:  $result")
    }

    operator fun Point.plus(o: Point): Point {
        return Point(x + o.x, y + o.y)
    }

    operator fun Point.times(n: Int): Point {
        return Point(x * n, y * n)
    }

    data class Point(val x: Int, val y: Int){
        fun setX(x: Int): Point {
            return Point(x, y)
        }

        fun setY(y: Int): Point {
            return Point(x, y)
        }
    }

    companion object {
        fun unreachable(): Nothing = throw Exception("Should not be reachable!")
    }
}