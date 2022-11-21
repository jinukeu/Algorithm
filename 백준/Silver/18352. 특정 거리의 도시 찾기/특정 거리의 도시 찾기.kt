import java.util.*

private const val INF = 1000_000_000
private lateinit var graph: MutableList<MutableList<Int>>
private lateinit var distance: IntArray

fun main(args: Array<String>) {
    val (n, m, k, x) = readln().split(" ").map { it.toInt() }
    distance = IntArray(n + 1) { INF }
    graph = MutableList(n + 1) { mutableListOf() }

    repeat(m) {
        val (a, b) = readln().split(" ").map { it.toInt() }
        graph[a].add(b)
    }

    dijkstra(x)

    var isNotExist = true
    for(city in distance.indices) {
        if(distance[city] == k) {
            println(city)
            isNotExist = false
        }
    }
    
    if(isNotExist) println(-1)
}

fun dijkstra(start: Int) {
    val q = PriorityQueue<Node>()
    q.add(Node(0, start))
    distance[start] = 0

    while(q.isNotEmpty()) {
        val (dist, now) = q.poll()

        if(distance[now] < dist) continue

        graph[now].forEach { next ->
            if(dist + 1 < distance[next]) {
                distance[next] = dist + 1
                q.add(Node(dist + 1, next))
            }
        }
    }
}

data class Node(val dist: Int, val now: Int) : Comparable<Node> {
    override fun compareTo(other: Node): Int = dist - other.dist
}