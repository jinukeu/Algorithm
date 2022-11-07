import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.abs
import kotlin.math.min

private val br = BufferedReader(InputStreamReader(System.`in`))
val graph = mutableListOf<List<Int>>()
val sets = mutableSetOf<String>()
val dirs = listOf(listOf(1, 0), listOf(0, 1), listOf(-1, 0), listOf(0, -1))

fun main(args: Array<String>) {
    repeat(5) {
        graph.add(br.readLine().split(" ").map { it.toInt() })
    }

    for(i in 0 until 5)
        for(j in 0 until 5) {
            dfs(i, j, "", 0)
        }
    
    print(sets.size)
}

fun dfs(prevI: Int, prevJ: Int, prevStr: String, cnt: Int) {
    if(cnt == 6) {
        sets.add(prevStr)
        return
    }

    for((di,dj) in dirs) {
        val newI = prevI + di
        val newJ = prevJ + dj

        if(newI !in 0 until 5 || newJ !in 0 until 5) continue
        dfs(newI, newJ, prevStr + "${graph[newI][newJ]}", cnt + 1)
    }
}