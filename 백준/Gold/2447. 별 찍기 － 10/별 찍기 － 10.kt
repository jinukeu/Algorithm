
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.abs

val br = BufferedReader(InputStreamReader(System.`in`))
val bw = BufferedWriter(OutputStreamWriter(System.out))

var graph = mutableListOf<MutableList<String>>()

var n = 0
var m = 0

var answer = 0

fun main() {
    val n = br.readLine().toInt()
    repeat(n) {
        graph.add(MutableList(n) { " " })
    }

    divideAndConquer(0, n, 0, n, n)

    graph.forEach {
        println(it.joinToString(""))
    }
}

fun divideAndConquer(startX: Int, endX: Int, startY: Int, endY: Int, beforeUnit: Int) {
    if(beforeUnit == 1) {
        for(i in startX until endX) {
            for(j in startY until endY) {
                graph[j][i] = "*"
            }
        }
        return
    }
    val unit = beforeUnit / 3

    //println("$startX $endX $startY $endY / $unit")

    for(i in 1 .. 3) {
        for(j in 1 .. 3) {
            if(i == 2 && j == 2) continue
            divideAndConquer(startX + unit * (i - 1), startX + unit * i, startY + unit * (j - 1), startY + unit * j, unit)
        }
    }
}

