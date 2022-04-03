import kotlin.system.exitProcess

fun main(args: Array<String>) {
    val (n, c) = readln().split(" ").map { it.toInt() }
    val historyArray = Array(n + 1) {Array(n + 1) { 0 } }
    repeat(c) {
        val (a, b) = readln().split(" ").map { it.toInt() }
        historyArray[a][b] = -1
        historyArray[b][a] = 1
    }

    for(k in 1..n)
        for(i in 1..n)
            for(j in 1..n) {
                if(historyArray[i][k] == historyArray[k][j] && historyArray[i][k] != 0) {
                    historyArray[i][j] = historyArray[i][k]
                    historyArray[j][i] = -historyArray[i][k]
                }
            }
    val s = readln().toInt()
    repeat(s) {
        val (a, b) = readln().split(" ").map { it.toInt() }
        println(historyArray[a][b])
    }
}