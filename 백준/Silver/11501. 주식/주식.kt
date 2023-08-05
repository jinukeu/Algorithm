import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

private val br = BufferedReader(InputStreamReader(System.`in`))
private val bw = BufferedWriter(OutputStreamWriter(System.out))

fun main() {
    val n = br.readLine().toInt()

    for(j in 0 until n) {
        val n1 = br.readLine().toInt()
        val stocks = br.readLine().split(" ").map { it.toLong() }

        val stockMap = mutableListOf<Pair<Int, Long>>()

        var mIdx = n1 - 1
        var m1 = stocks[mIdx]

        stockMap.add(Pair(mIdx, m1))

        for (ri in n1 - 2 downTo  0) {
            if(stocks[ri] >= m1) {
                mIdx = ri
                m1 = stocks[ri]
                stockMap.add(Pair(mIdx, m1))
            }
        }

        var ans = 0L
        var startIdx = 0

        var stockIdx = stockMap.size - 1

        while (stockIdx >= 0) {
            val (maxIdx, m) = stockMap[stockIdx]

            var s = 0L

            for(i in startIdx until maxIdx) {
                s += stocks[i]
            }

            val t = m * (maxIdx - startIdx) - s
            if(t > 0)
            ans += m * (maxIdx - startIdx) - s

            startIdx = maxIdx + 1
            stockIdx -= 1
        }

        bw.write("$ans\n")
    }

    bw.flush()
}