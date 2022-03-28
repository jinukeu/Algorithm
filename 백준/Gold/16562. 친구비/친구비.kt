import kotlin.math.min
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    val (n, m, k) = readln().split(" ").map { it.toInt() }
    val friendCost = (listOf(0) + readln().split(" ").map { it.toInt() }).toMutableList()
    val parent = Array(n + 1) {it}
    val root = mutableSetOf<Int>()
    repeat(m) {
        val (a,b) = readln().split(" ").map { it.toInt() }
        unionParent(parent, a, b)
    }
    for(i in 1..n) {
        val p = findParent(parent, i)
        root.add(p)
        friendCost[p] = min(friendCost[p], friendCost[i])
    }
    var cost = 0
    root.forEach {
        cost += friendCost[it]
    }
    if(cost <= k) {
        print(cost)
    } else {
        print("Oh no")
    }
}

fun findParent(parent: Array<Int>, x: Int): Int {
    if(parent[x] != x)
        parent[x] = findParent(parent, parent[x])
    return parent[x]
}

fun unionParent(parent: Array<Int>, a: Int, b: Int) {
    val x = findParent(parent, a)
    val y = findParent(parent, b)
    if(x < y)
        parent[y] = x
    else
        parent[x] = y
}