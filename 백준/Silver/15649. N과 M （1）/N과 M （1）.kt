import java.io.BufferedReader
import java.io.InputStreamReader


private val br = BufferedReader(InputStreamReader(System.`in`))

fun main(args: Array<String>) {
    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    dfs(0, n, mutableListOf(), m)
}

fun dfs(startNum: Int, endNum: Int, stk: MutableList<Int>, maxCount: Int) {
    if(stk.size == maxCount) {
        println(stk.joinToString(" "))
        return
    }

    for(next in 1 .. endNum) {
        if(next in stk) continue
        stk.add(next)
        dfs(next, endNum, stk, maxCount)
        stk.removeLast()
    }
}