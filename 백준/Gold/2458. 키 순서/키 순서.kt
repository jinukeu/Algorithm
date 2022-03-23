import java.io.BufferedReader
import java.util.*

class Main {}

fun main(args: Array<String>) {
    val (n, m) = readln().split(" ").map {it.toInt()}
    val array = Array(n + 1) { IntArray(n + 1) }
    repeat(m) {
        val (a, b) = readln().split(" ").map {it.toInt()}
        array[a][b] = 1
    }

    for(k in 1..n)
        for(i in 1..n) {
            array[i][i] = 1
            for (j in 1..n) {
                if (array[i][k] == 1 && array[k][j] == 1)
                    array[i][j] = 1
            }
        }
    var cnt = 0
    for(i in 1..n){
        for(j in 1..n){
            if(array[i][j] == 0){
                if(array[j][i] == 0) break
            }
            if(j==n)    cnt++
        }
    }
    print(cnt)
}