import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.Collections.max
import kotlin.contracts.contract

private val br = BufferedReader(InputStreamReader(System.`in`))

fun main(args: Array<String>) {
    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    val dnaMapCountList = List(m) { mutableMapOf("A" to 0, "T" to 0, "G" to 0, "C" to 0) }

    repeat(n) {
        val dnaString = br.readLine().split("").subList(1, m + 1)
        for (i in 0 until m) {
            dnaMapCountList[i][dnaString[i]] = dnaMapCountList[i][dnaString[i]]!!.plus(1)
        }
    }

    var ansDna = ""
    var ansCount = 0

    dnaMapCountList.forEach { dnaMapCount ->
        val maxNum = max(dnaMapCount.values)
        val key = dnaMapCount.getKey(maxNum)
        ansDna += key
        ansCount += dnaMapCount.values.sum() - dnaMapCount[key]!!
    }
    println(ansDna)
    println(ansCount)
}

fun MutableMap<String, Int>.getKey(value: Int): String {
    var returnKey = "T"
    this.keys.forEach { key ->
        if(this[key] == value && key < returnKey) returnKey = key
    }
    return returnKey
}