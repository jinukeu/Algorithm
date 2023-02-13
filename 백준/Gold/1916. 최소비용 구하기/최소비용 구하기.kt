import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*


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
        val curNode = pq.poll() // 인접 노드 중 가장 거리가 짧은 노드를 꺼냄
        if(distance[curNode.id] < curNode.cost) continue

        for(adjNode in graph[curNode.id]) {
            val cost = curNode.cost + adjNode.cost // 현재 노드를 거쳐서 인접 노드로 가는 비용
            if(cost < distance[adjNode.id]) { // 현재 노드를 거쳐서 인접 노드로 가는 비용이 기존 보다 더 빠르다면 
                distance[adjNode.id] = cost // 갱신
                pq.add(Node(adjNode.id, cost)) // 인접 노드를 queue에 넣어줌
            }
        }
    }

    println(distance[end])
}

data class Node(
    val id: Int,
    val cost: Int
) : Comparable<Node> {
    override fun compareTo(other: Node): Int {
        return cost - other.cost
    }
}