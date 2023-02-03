import java.io.BufferedReader
import java.io.InputStreamReader


private val br = BufferedReader(InputStreamReader(System.`in`))
private lateinit var weight: List<Int>
var answer = 0

fun main(args: Array<String>) {
    val (n, k) = br.readLine().split(" ").map { it.toInt() }
    weight = br.readLine().split(" ").map { it.toInt() }
    dfs(n, mutableListOf(), k, 500)
    print(answer)
}

fun dfs(maxCount: Int, idxStk: MutableList<Int>, loss: Int, currentWeight: Int) {
    if(currentWeight < 500) return

    if(idxStk.size == maxCount) {
        answer += 1
        return
    }

    for(idx in weight.indices) {
        if(idx in idxStk) continue
        idxStk.add(idx)
        dfs(maxCount, idxStk, loss, currentWeight + weight[idx] - loss)
        idxStk.removeLast()
    }
}