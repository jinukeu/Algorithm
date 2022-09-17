import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.Collections.max
import kotlin.contracts.contract

private val br = BufferedReader(InputStreamReader(System.`in`))

fun main(args: Array<String>) {
    val n = br.readLine().toInt()
    val levelList = br.readLine().split(" ").map { it.toInt() }

    val maxLevel = max(levelList)
    var s = levelList.sum()
    s += maxLevel * n - maxLevel * 2
    print(s)
}