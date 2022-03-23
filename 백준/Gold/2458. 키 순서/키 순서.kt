import java.io.BufferedReader
import java.util.*

class Main {}

fun main(args: Array<String>) {
    val (n, m) = readln().split(" ").map {it.toInt()}
    val array = arrayListOf<Array<Int>>()
    repeat(n + 1) { array.add(Array<Int>(n + 1) { 0 }) }
    repeat(m) {
        val (a, b) = readln().split(" ").map {it.toInt()}
        array[a][b] = 1
        array[b][a] = 2
    }

    for(k in 1..n)
        for(i in 1..n)
            for(j in 1..n) {
                if(i == j) continue
                if(array[i][k] == 1 && array[k][j] == 1)
                    array[i][j] = 1
                if(array[i][k] == 2 && array[k][j] == 2)
                    array[i][j] = 2
            }
    var cnt = 0

    array.forEach { a ->
        if((a.count {
            it == 0
        }) == 2)
            cnt ++
    }
    print(cnt)
}