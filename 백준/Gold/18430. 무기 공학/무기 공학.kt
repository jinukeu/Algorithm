
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Math.*
import java.util.Collections

val br = BufferedReader(InputStreamReader(System.`in`))
val bw = BufferedWriter(OutputStreamWriter(System.out))

// x, y
val leftDown = listOf(listOf(0, -1), listOf(-1, 0))
val rightDown = listOf(listOf(0, -1), listOf(1, 0))
val leftUp = listOf(listOf(0, 1), listOf(-1, 0))
val rightUp = listOf(listOf(0, 1), listOf(1, 0))

val dirs = listOf(leftDown, leftUp, rightDown, rightUp)
var visited = mutableListOf<MutableList<Boolean>>()
var graph = mutableListOf<List<Int>>()

var n = 0
var m = 0

var answer = 0

fun main() {
    val (a, b) = br.readLine().split(" ").map { it.toInt() }
    n = a
    m = b

    repeat(n) {
        graph.add(br.readLine().split(" ").map { it.toInt() })
    }

    repeat(n) {
        visited.add(MutableList(m) { false })
    }

    backtracking(0, 0, 0)

    println(answer)
}

fun backtracking(sum: Int, prevI: Int, prevJ: Int) {
    for(i in prevI until n) {
        val stJ = if(i == prevI) prevJ else 0
        for(j in stJ until m) {

            if(visited[i][j]) continue

            for(dir in dirs) {

                var possible = true
                var temp = graph[i][j] * 2

                for((di, dj) in dir) {
                    val ni = i + di
                    val nj = j + dj

                    if((ni in 0 until n && nj in 0 until m).not() || visited[ni][nj]) {
                        possible = false
                        break
                    }
                }

                if(possible.not()) continue

                visited[i][j] = true
                for((di, dj) in dir) {
                    val ni = i + di
                    val nj = j + dj

                    visited[ni][nj] = true
                    temp += graph[ni][nj]
                }

                answer = max(answer, sum + temp)

                backtracking(sum + temp,i,j)

                visited[i][j] = false
                for((di, dj) in dir) {
                    val ni = i + di
                    val nj = j + dj

                    visited[ni][nj] = false
                }

            }
        }
    }
}

