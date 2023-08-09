import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.out))

private val parent: MutableList<Int> = mutableListOf()

fun main() {
    val (n, m) = br.readLine().split(" ").map { it.toInt() }

    repeat(n + 1) {
        parent.add(it)
    }

    repeat(m) {
        val (x, y) = br.readLine().split(" ").map { it.toInt() }
        unionParent(x, y)
    }

    val edges = mutableListOf<Triple<Int, Int, Int>>()

    repeat(n) {
        val costs = br.readLine().split(" ").map { it.toInt() }
        costs.forEachIndexed { index ,cost ->
            if(cost != 0 && it != 0 && index != 0) {
                edges.add(Triple(it + 1, index + 1, cost))
            }
        }
    }

    edges.sortBy { it.third }

    var result = 0
    val ans = mutableListOf<Pair<Int, Int>>()

    edges.forEach { (a, b, cost) ->
        if(findParent(a) != findParent(b)) {
            unionParent(a, b)
            result += cost
            ans.add(Pair(a, b))
        }
    }

    if(result == Int.MAX_VALUE) {
        println("0 0")
        return
    }

    println("$result ${ans.size}")
    ans.forEach { (a, b) ->
        println("$a $b")
    }
}

fun findParent(x: Int): Int {
    if(parent[x] != x) parent[x] = findParent(parent[x])
    return parent[x]
}

fun unionParent(a: Int, b: Int) {
    val pa = findParent(a)
    val pb = findParent(b)

    if(pa < pb)
        parent[pb] = pa
    else parent[pa] = pb
}