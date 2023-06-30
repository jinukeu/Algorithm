
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.PriorityQueue
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow

val br = BufferedReader(InputStreamReader(System.`in`))
val bw = BufferedWriter(OutputStreamWriter(System.out))

val MAX_VALUE = 100

fun main() {
    val (n, m, a, b, c) = br.readLine().split(" ").map { it.toInt() }
    val graph = Array(n + 1) { mutableListOf<Node>() }

    repeat(m) {
        val (x, y, cost) = br.readLine().split(" ").map { it.toInt() }
        graph[x].add(Node(y, cost, c))
        graph[y].add(Node(x, cost, c))
    }

    val fees = MutableList(n + 1) { MAX_VALUE }

    val pq = PriorityQueue<Node> { node1, node2 ->
        when {
            node1.cost > node2.cost -> 1
            node1.cost < node2.cost -> -1
            else -> 0
        }
    }

    pq.add(Node(a, 0, c))
    fees[a] = 0

    while (pq.isNotEmpty()) {
        val (now, cost, remain) = pq.poll()
        if(fees[now] < cost) continue

        for(next in graph[now]) {
            val minFee = max(next.cost, fees[now])
            if(remain - next.cost >= 0 && fees[next.to] > minFee) {
                fees[next.to] = min(minFee, fees[next.to])
                pq.add(Node(next.to, next.cost, remain - next.cost))
//                println("$now -> ${next.to}")
            }
        }
    }

    println(if(fees[b] == 100) -1 else fees[b])
}

data class Node(
    val to: Int,
    val cost: Int,
    var remain: Int
)

