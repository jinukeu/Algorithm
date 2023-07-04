
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
        val (x, y) = br.readLine().split(" ").map { it.toInt() }
        graph[x].add(y)
        indegree[y] += 1
    }

    val pq = ArrayDeque<Int>()
    var semester = 1
    val answer = MutableList(n + 1) { 0 }

    for (i in 1 .. n) {
        if(indegree[i] == 0) {
            pq.add(i)
            answer[i] = semester
        }
    }

    while (pq.isNotEmpty()) {
        semester += 1
        repeat(pq.size) {
            val now = pq.removeFirst()

            for(next in graph[now]) {
                indegree[next] -= 1
                if (indegree[next] == 0) {
                    pq.add(next)
                    answer[next] = semester
                }
            }
        }
    }

    println(answer.subList(1, n + 1).joinToString(" "))
}