import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.system.exitProcess


private val br = BufferedReader(InputStreamReader(System.`in`))
val INF = 1000000000

fun main(args: Array<String>) {
    val n = br.readLine().toInt()
    val m = br.readLine().toInt()

    val graph = MutableList(n + 1) { mutableListOf<Node>() }
    val distance = MutableList(n + 1) { INF }

    repeat(m) {
        val (a, b, c) = br.readLine().split(" ").map { it.toInt() }
        graph[a].add(Node(b, c))
    }

    val (start, end) = br.readLine().split(" ").map { it.toInt() }

    val pq = PriorityQueue<Node>()
    pq.add(Node(start, 0))
    distance[start] = 0

    while (pq.isNotEmpty()) {
        val curNode = pq.poll()
        val dist = curNode.cost
        val now = curNode.dest
        if(distance[now] < dist) continue

        for(nextNode in graph[now]) {
            val cost = dist + nextNode.cost
            if(cost < distance[nextNode.dest]) {
                distance[nextNode.dest] = cost
                pq.add(Node(nextNode.dest, cost))
            }
        }
    }

    println(distance[end])
}

data class Node(
    val dest: Int,
    val cost: Int
) : Comparable<Node> {
    override fun compareTo(other: Node): Int {
        return cost - other.cost
    }
}