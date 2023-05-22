import java.io.BufferedReader
import java.io.InputStreamReader


private val br = BufferedReader(InputStreamReader(System.`in`))

fun main(args: Array<String>) {
    while(true) {
        val (n, k) = br.readLine().split(" ").map { it.toInt() }
        if(n == 0 && k == 0) break

        val inputs = listOf(-1) + br.readLine().split(" ").map { it.toInt() }
        val parents = MutableList<Int>(n + 1) { 0 }
        parents[0] = -1

        var parentPointer = -1
        var answerPointer = 0

        for(childPointer in 1 until inputs.size) {
            if(inputs[childPointer] == k) answerPointer = childPointer
            if(inputs[childPointer] != inputs[childPointer - 1] + 1) parentPointer += 1
            parents[childPointer] = parentPointer
        }

        var answer = 0

        for(i in 1 until parents.size) {
            if(parents[answerPointer] != parents[i] && parents[parents[answerPointer]] == parents[parents[i]]) answer += 1
        }

        println(answer)
    }
}

class Node(
    var parent: Int = 0,
    var value: Int = 0
)