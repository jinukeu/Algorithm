import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.Comparator

private val br = BufferedReader(InputStreamReader(System.`in`))
private lateinit var parent: MutableList<Int>

fun main(args: Array<String>) {
    while (true) {
        val (m, n) = br.readLine().split(" ").map { it.toInt() }
        if(m == 0 && n == 0) break
        kruskal(m, n)
    }
}

fun kruskal(m: Int, n: Int) {
    parent = MutableList(m + 1) { it }

    val edges = mutableListOf<NodeInfo>()
    var result = 0

    repeat(n) {
        val (x, y, z) = br.readLine().split(" ").map { it.toInt() }
        edges.add(NodeInfo(x, y, z))
    }

    val comparator: Comparator<NodeInfo> = compareBy { it.z }
    edges.sortWith(comparator)

    edges.forEach { (x, y, z) ->
        if(findParent(x) != findParent(y)) unionParent(x, y)
        else result += z
    }

    println(result)
}

fun findParent(x: Int): Int {
    if(parent[x] != x) parent[x] = findParent(parent[x])
    return parent[x]
}

fun unionParent(a: Int, b: Int) {
    val parentA = findParent(a)
    val parentB = findParent(b)

    if(a < b) parent[parentB] = parentA
    else parent[parentA] = parentB
}

data class NodeInfo(
    val x: Int,
    val y: Int,
    val z: Int
)