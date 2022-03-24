import java.util.*
import kotlin.collections.ArrayList

class Main {}

private const val INF = 1e9.toInt()

fun main(args: Array<String>) {
    val t = readln().toInt()
    repeat(t) {
        val (n, d, c) = readln().split(" ").map { it.toInt() }
        val graph = Array(n + 1) { ArrayList<Node>() }

        repeat(d) {
            val (a, b, s) = readln().split(" ").map { it.toInt() }
            graph[b].add(Node(a, s))
        }

        val pq = PriorityQueue<Node>()
        pq.offer(Node(c, 0))

        val distance = IntArray(n + 1) { INF }
        distance[c] = 0

        while (pq.isNotEmpty()) {
            val node = pq.poll()
            if (distance[node.computer] < node.second) continue
            graph[node.computer].forEach {
                if (it.second + node.second < distance[it.computer]) {
                    distance[it.computer] = it.second + node.second
                    pq.add(Node(it.computer, it.second + node.second))
                }
            }
        }

        val cnt = distance.count { it != INF }
        println(
            "$cnt ${
                distance.maxOf {
                    if (it == INF) 0
                    else it
                }
            }"
        )
    }
}

data class Node(
    val computer: Int,
    val second: Int
) : Comparable<Node> {
    override fun compareTo(other: Node): Int {
        return second - other.second
    }
}
