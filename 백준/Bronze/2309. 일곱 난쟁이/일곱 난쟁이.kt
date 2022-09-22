import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.math.*

private val br = BufferedReader(InputStreamReader(System.`in`))


fun main(args: Array<String>) {
    val height = Array(9) { 0 }
    repeat(9) {
        height[it] = br.readLine().toInt()
    }
    height.sort()
    val s = height.sum()
    var fake = listOf(0, 0)
    for(i in 0 until 9)
        for(j in 0 until 9) {
            if(i == j) continue
            if(height[i] + height[j] == s - 100) fake = listOf(i, j)
        }

    for(i in 0 until 9) {
        if (i in fake) continue
        println(height[i])
    }
}