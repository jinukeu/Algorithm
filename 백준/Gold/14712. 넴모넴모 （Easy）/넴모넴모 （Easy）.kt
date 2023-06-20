
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Math.*
import java.util.Collections

val br = BufferedReader(InputStreamReader(System.`in`))
val bw = BufferedWriter(OutputStreamWriter(System.out))

// x, y
val leftDown = listOf(listOf(0, -1), listOf(-1, -1), listOf(-1, 0))
val rightDown = listOf(listOf(0, -1), listOf(1, -1), listOf(1, 0))
val leftUp = listOf(listOf(0, 1), listOf(-1, 1), listOf(-1, 0))
val rightUp = listOf(listOf(0, 1), listOf(1, 1), listOf(1, 0))

val dirs = listOf(leftDown, leftUp, rightDown, rightUp)
var graph = mutableListOf<MutableList<Boolean>>()

var n = 0
var m = 0

var answer = 0

fun main() {
    val (a, b) = br.readLine().split(" ").map { it.toInt() }
    n = a
    m = b

    repeat(n) {
        graph.add(MutableList(m) { false })
    }

    backtracking(0, 0)

//    println("answer!!")
//    answer.forEach { child ->
//        child.forEach {
//            println(it)
//        }
//        println("----")
//    }


    println(answer + 1)
}

fun backtracking(prevI: Int, pervJ: Int) {
    for(i in prevI until n) {
        val stJ = if(i == prevI) pervJ else 0
        for(j in stJ until m) {
            if(graph[i][j]) continue
            var possible = false
            for(dir in dirs) {
                possible = false
                for((di, dj) in dir) {
                    val ni = i + di
                    val nj = j + dj
                    if(ni in 0 until n && nj in 0 until m) {
                        if(graph[ni][nj].not()) {
                            possible = true
                            break
                        }
                    } else {
                        possible = true
                        break
                    }
                }

                if(!possible) break
            }

            if(possible) {
                graph[i][j] = true

//                println("i = $i j = $j previ = $prevI")
//                graph.forEach {
//                    println(it)
//                }
//                println("-----")



                var isAlreadyInAnswer = false

//                answer.forEach { answerChild ->
//                    var isAlreadyInAnswerTemp = true
//                    answerChild.zip(graph).forEach { (a, b) ->
//                        a.zip(b).forEach { (aChild, bChild) ->
//                            if(aChild != bChild) isAlreadyInAnswerTemp = false
//                        }
//                    }
//                    if(isAlreadyInAnswerTemp) isAlreadyInAnswer = true
//                }

                if(!isAlreadyInAnswer) {
                    answer += 1
                    backtracking(i, j)
                }
                graph[i][j] = false
            }
        }
    }

}

