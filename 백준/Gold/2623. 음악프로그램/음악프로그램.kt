import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList
import java.util.PriorityQueue
import java.util.Queue
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow

val br = BufferedReader(InputStreamReader(System.`in`))
val bw = BufferedWriter(OutputStreamWriter(System.out))

fun main() {
    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    val graph = Array(n + 1) { mutableListOf<Int>() }
    val indegree = MutableList(n + 1) { 0 }

    repeat(m) {
        val list = br.readLine().split(" ").map { it.toInt() }

        for (idx in 1 until list[0]) {
            graph[list[idx]].add(list[idx + 1])
            indegree[list[idx + 1]] += 1
        }
    }

//    graph.forEach {
//        println(it)
//    }
//    println(indegree)

    val pq = ArrayDeque<Int>()
    val answer = mutableListOf<Int>()

    for (i in 1..n) {
        if (indegree[i] == 0) {
            pq.add(i)
        }
    }

    while (pq.isNotEmpty()) {
        val now = pq.removeLast()
        answer.add(now)

        for (next in graph[now]) {
            indegree[next] -= 1
            if (indegree[next] == 0) {
                pq.add(next)
            }
        }
    }

    if (answer.size == n)
        answer.forEach { println(it) }
    else println(0)
}