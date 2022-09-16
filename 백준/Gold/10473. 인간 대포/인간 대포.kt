import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.*

private val br = BufferedReader(InputStreamReader(System.`in`))
private lateinit var visited: MutableList<Boolean>
private lateinit var nodeList: MutableList<Node>
private var distance = mutableListOf<Double>()

fun main(args: Array<String>) {
    val (startX, startY) = br.readLine().split(" ").map { it.toDouble() }
    val start = Node(startX, startY)

    val (goalX, goalY) = br.readLine().split(" ").map { it.toDouble() }
    val goal = Node(goalX, goalY)

    val count = br.readLine().toInt()

    visited = MutableList(count + 1) { false }

    nodeList = mutableListOf()

    repeat(count) {
        val (x, y) = br.readLine().split(" ").map { it.toDouble() }
        nodeList.add(Node(x, y))
    }

    nodeList.add(goal)

    nodeList.forEach { node ->
        distance.add(getDistance(start, node) / 5)
    }

    repeat(count + 1) {
        val now = getSmallestNode()
        visited[now] = true

        for(i in distance.indices) {
            if(i == now) continue
            val cost = distance[now] + getTime(nodeList[now], nodeList[i])
            if(cost < distance[i]) distance[i] = cost
        }
    }
    println(String.format("%6f",distance[count]))
}

data class Node(
    val x: Double, val y: Double
)

fun getTime(start: Node, dest: Node): Double {
    val distance = getDistance(start, dest)
    val walk = distance / 5
    val cannon = abs(distance - 50) / 5 + 2
    return if(walk < cannon) walk else cannon
}

fun getDistance(start: Node, dest: Node): Double {
    return sqrt((start.x - dest.x).pow(2) + (start.y - dest.y).pow(2))
}

fun getSmallestNode(): Int {
    var min = Double.MAX_VALUE
    var index = 0
    for(i in nodeList.indices) {
        if(distance[i] < min && !visited[i]) {
            min = distance[i]
            index = i
        }
    }
    return index
}