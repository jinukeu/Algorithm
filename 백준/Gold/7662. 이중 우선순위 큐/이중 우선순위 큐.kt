import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.Collections
import java.util.PriorityQueue
import java.util.TreeMap


private val br = BufferedReader(InputStreamReader(System.`in`))

fun main(args: Array<String>) {
    val t = br.readLine().toInt()
    repeat(t) {
        doTestCase()
    }
}

fun doTestCase() {
    val treeMap = TreeMap<Int, Int>()

    val k = br.readLine().toInt()
    repeat(k) {
        val input = br.readLine().split(" ")
        val op = input[0]
        val num = input[1].toInt()
        if(op == "D" && treeMap.isNotEmpty()) {
            val key = if(num == 1) treeMap.lastKey() else treeMap.firstKey()
            if(treeMap[key] == 1) {
                treeMap.remove(key)
            } else {
                treeMap[key] = treeMap[key]!! - 1
            }
        }
        else if(op == "I") {
            treeMap[num] = treeMap.getOrDefault(num, 0) + 1
        }
    }

    if(treeMap.isEmpty()) {
        println("EMPTY")
    } else {
        println("${treeMap.lastKey()} ${treeMap.firstKey()}")
    }
}
