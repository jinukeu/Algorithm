import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Math.abs
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Collections
import kotlin.system.exitProcess

val br = BufferedReader(InputStreamReader(System.`in`))
val bw = BufferedWriter(OutputStreamWriter(System.out))

fun main() {
    getAnswer()
}

fun getAnswer() {
    val n = br.readLine().toInt()
    val numbers = listOf(0) + br.readLine().split(" ").map { it.toInt() }
    val dp = Array(n + 1) { LongArray(21) }

    dp[1][numbers[1]] = 1
    for(index in 2 .. n) {
        for(i in 0 .. 20) {
            if(i - numbers[index] in 0 .. 20) {
                dp[index][i - numbers[index]] += dp[index - 1][i]
            }
            if(i + numbers[index] in 0 .. 20) {
                dp[index][i + numbers[index]] += dp[index - 1][i]
            }
        }
    }

//    dp.forEach {
//        println(it.contentToString())
//    }
    println(dp[n - 1][numbers[n]])
}