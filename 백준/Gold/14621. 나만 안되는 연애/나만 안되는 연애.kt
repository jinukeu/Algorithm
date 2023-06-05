import com.sun.xml.internal.fastinfoset.util.StringArray
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Math.abs
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Collections
import kotlin.system.exitProcess

val br = BufferedReader(InputStreamReader(System.`in`))
val bw = BufferedWriter(OutputStreamWriter(System.out))

var parent: IntArray = intArrayOf()
var gender: List<String> = emptyList()

fun main() {
    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    parent = IntArray(n + 1) { it }
    gender = listOf("") + br.readLine().split(" ")
    val graph = mutableListOf<Node>()

    repeat(m) {
        val (u, v, d) = br.readLine().split(" ").map { it.toInt() }
        graph.add(Node(u, v, d))
    }

    graph.sortBy { it.distance }
    

    var answer = 0
    var count = 0
    graph.forEach { node ->
        val (a, b, cost) = node
        if(findParent(a) != findParent(b) && gender[a] != gender[b])
        {
            unionParent(a, b)
            answer += cost
            count ++
        }
    }

    println(if(count == n - 1) answer else -1)
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