import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.Collections
import java.util.PriorityQueue
import java.util.TreeMap


private val br = BufferedReader(InputStreamReader(System.`in`))

fun main(args: Array<String>) {
    //key가 난이도
    val treeMap = TreeMap<Int, TreeMap<Int, Int>>()
    val n = br.readLine().toInt()
    repeat(n) {
        val (questionNum, level) = br.readLine().split(" ").map { it.toInt() }
        if (treeMap.containsKey(level)) {
            val questionList = treeMap[level]!!
            questionList[questionNum] = 0
        } else {
            val questionList = TreeMap<Int, Int>()
            questionList[questionNum] = 0
            treeMap[level] = questionList
        }
    }

    val m = br.readLine().toInt()
    val solved = mutableListOf<Int>()
    repeat(m) {
        val input = br.readLine().split(" ")
        val command = input[0]

        when (command) {
            "add" -> {
                val questionNum = input[1].toInt()
                val level = input[2].toInt()

                if (treeMap.containsKey(level)) {
                    val questionList = treeMap[level]!!
                    questionList[questionNum] = 0
                } else {
                    val questionList = TreeMap<Int, Int>()
                    questionList[questionNum] = 0
                    treeMap[level] = questionList
                }
            }
            "recommend" -> {
                var key = if (input[1] == "1") treeMap.lastKey() else treeMap.firstKey()
                var questionList = treeMap[key]!!

                while (true) {
//                    println("D: $questionList")
                    val question = if (input[1] == "1") questionList.lastKey() else questionList.firstKey()
                    if (question in solved) {
                        questionList.remove(question)
                        solved.remove(question)
                        if(questionList.isEmpty()) {
                            treeMap.remove(key)
                            key = if (input[1] == "1") treeMap.lastKey() else treeMap.firstKey()
                            questionList = treeMap[key]!!
                        }
                    }
                    else {
                        println(question)
                        break
                    }
                }
            }
            "solved" -> {
                solved.add(input[1].toInt())
            }
        }
    }
}
