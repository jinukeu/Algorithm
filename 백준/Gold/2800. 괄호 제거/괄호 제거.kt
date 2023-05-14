import java.io.BufferedReader
import java.io.InputStreamReader


private val br = BufferedReader(InputStreamReader(System.`in`))
val bracketIdxList = mutableListOf<Bracket>()
var visited: MutableList<Boolean> = mutableListOf()
val answer = mutableSetOf<String>()

fun main(args: Array<String>) {
    val givenFormula = br.readLine().split("").filter { it != "" }.toMutableList()
    val startIdxList = mutableListOf<Int>()
    val endIdxList = mutableListOf<Int>()

    givenFormula.forEachIndexed { index, s ->
        if (s == "(") startIdxList.add(index)
        if (s == ")") bracketIdxList.add(Bracket(startIdxList.removeLast(), index))
    }

    startIdxList.zip(endIdxList.reversed()).forEach { pair ->
        
    }

    visited = MutableList(bracketIdxList.size) { false }

    backTracking(givenFormula, 0)

    answer.toList().sorted().forEachIndexed { idx, it ->
        print(it)
        if(idx != answer.size - 1) println()
    }
}

data class Bracket(
    val startIdx: Int,
    val endIdx: Int
)

fun backTracking(givenFormula: MutableList<String>, prevIdx: Int) {

    for(index in prevIdx until bracketIdxList.size) {
        val (startIdx, endIdx) = bracketIdxList[index]
        givenFormula[startIdx] = ""
        givenFormula[endIdx] = ""
        visited[index] = true

        //println("D : " + givenFormula.joinToString(""))
        answer.add(givenFormula.joinToString(""))
        backTracking(givenFormula, index + 1)

        visited[index] = false
        givenFormula[startIdx] = "("
        givenFormula[endIdx] = ")"
    }
}