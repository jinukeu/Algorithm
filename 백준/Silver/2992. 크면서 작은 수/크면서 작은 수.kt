import java.io.BufferedReader
import java.io.InputStreamReader

private val br = BufferedReader(InputStreamReader(System.`in`))
private var isUsed = mutableListOf<Boolean>()
private var x = listOf<Char>()
private val ans = mutableListOf<Int>()

fun main(args: Array<String>) {
    x = br.readLine().toList()
    isUsed = MutableList(x.size) { false }

    backtracking("", 0)

    ans.sort()

    ans.forEach {
        if(it > x.joinToString("").toInt()) {
            println(it)
            return
        }
    }

    println(0)
}

fun backtracking(prevStr: String, cnt: Int) {
    if(cnt == x.size && prevStr[0] != '0') {
        ans.add(prevStr.toInt())
    }

    for(i in x.indices) {
        if(isUsed[i]) continue
        isUsed[i] = true
        backtracking(prevStr + x[i], cnt + 1)
        isUsed[i] = false
    }
}