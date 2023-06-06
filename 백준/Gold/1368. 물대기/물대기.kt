import com.sun.xml.internal.fastinfoset.util.StringArray
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Math.abs
import java.lang.Math.min
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Collections
import kotlin.system.exitProcess

val br = BufferedReader(InputStreamReader(System.`in`))
val bw = BufferedWriter(OutputStreamWriter(System.out))

var parent: IntArray = intArrayOf()
var self: BooleanArray = booleanArrayOf()

fun main() {
    val n = br.readLine().toInt()
    parent = IntArray(n + 1) { it }

    self = BooleanArray(n + 1) { false }
    val graph = mutableListOf<Node>()

    repeat(n) {
        val cost = br.readLine().toInt()
        graph.add(Node(0, it + 1, cost))
    }

    repeat(n) { idx ->
        val costs = br.readLine().split(" ").map { it.toInt() }

        costs.forEachIndexed { index, cost ->
            if(idx != index && cost != 0) graph.add(Node(idx + 1, index + 1, cost))
        }
    }

    graph.sortBy { it.distance }


    var answer = 0
    graph.forEach { node ->
        val (a, b, cost) = node
        val pa = findParent(a)
        val pb = findParent(b)
        if(pa != pb)
        {
            unionParent(pa, pb)
            answer += cost
        }
    }


    println(answer)
}

fun findParent(x: Int): Int {
    if(x != parent[x]) parent[x] = findParent(parent[x])
    return parent[x]
}

fun unionParent(a: Int, b: Int) {
    val na = findParent(a)
    val nb = findParent(b)

    if(na < nb) parent[nb] = na
    else parent[na] = nb
}

data class Node(
    val start: Int,
    val destination: Int,
    val distance: Int
)