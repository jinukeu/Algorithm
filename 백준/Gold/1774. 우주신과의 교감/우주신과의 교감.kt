import java.text.DecimalFormat
import kotlin.math.*

fun main(args: Array<String>) {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val pointList = arrayListOf<Pair<Int, Int>>(Pair(0, 0))
    val connectList = arrayListOf<Pair<Int, Int>>()
    repeat(n) {
        val (a, b) = readln().split(" ").map { it.toInt() }
        pointList.add(Pair(a, b))
    }

    val nodeList = arrayListOf<Node>()
    val parent = Array(n + 1) { it }
    var result = 0.0

    repeat(m) {
        val (a, b) = readln().split(" ").map { it.toInt() }
        connectList.add(Pair(a, b))
        connectList.add(Pair(b, a))
    }

    for(i in 1..n)
        for(j in i + 1..n) {
            val dist = if(Pair(i, j) in connectList) 0.0 else sqrt((pointList[i].first - pointList[j].first).toDouble().pow(2)
                    + (pointList[i].second - pointList[j].second).toDouble().pow(2))
            nodeList.add(Node(i, j, dist))
        }

    nodeList.sortBy { it.cost }

    for((a, b, dist) in nodeList) {
        if(findParent(parent, a) != findParent(parent, b)) {
            unionParent(parent, a, b)
            result += dist
        }
    }

    print(String.format("%.2f", result))
}

data class Node(
    val a: Int,
    val b: Int,
    val cost: Double
)

fun findParent(parent: Array<Int>, x: Int): Int {
    if(parent[x] != x)
        parent[x] = findParent(parent, parent[x])
    return parent[x]
}

fun unionParent(parent: Array<Int>, a: Int, b: Int) {
    val x = findParent(parent, a)
    val y = findParent(parent, b)
    if (x < y)
        parent[y] = x
    else
        parent[x] = y
}