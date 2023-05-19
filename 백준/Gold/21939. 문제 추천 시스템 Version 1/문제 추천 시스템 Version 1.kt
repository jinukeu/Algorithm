import sun.reflect.generics.tree.Tree
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.Collections
import java.util.PriorityQueue
import java.util.TreeMap


private val br = BufferedReader(InputStreamReader(System.`in`))

fun TreeMap<Int, TreeMap<Int, Int>>.addQuestion(level: Int, questionNum: Int) {
    if (this.containsKey(level)) {
        val questionList = this[level]!!
        questionList[questionNum] = 0
    } else {
        val questionList = TreeMap<Int, Int>()
        questionList[questionNum] = 0
        this[level] = questionList
    }
}

fun <T> TreeMap<Int, T>.getKey(input: String): Int {
    return if (input == "1") this.lastKey() else this.firstKey()
}

fun main(args: Array<String>) {
    val treeMap = TreeMap<Int, TreeMap<Int, Int>>()
    val n = br.readLine().toInt()
    repeat(n) {
        val (questionNum, level) = br.readLine().split(" ").map { it.toInt() }
        treeMap.addQuestion(level, questionNum)
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
                treeMap.addQuestion(level, questionNum)
            }
            "recommend" -> {
                var key = treeMap.getKey(input[1])
                var questionList = treeMap[key]!!

                while (true) {
                    val question = questionList.getKey(input[1])
                    if (question in solved) {
                        questionList.remove(question)
                        solved.remove(question)
                        if(questionList.isEmpty()) {
                            treeMap.remove(key)
                            key = treeMap.getKey(input[1])
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
