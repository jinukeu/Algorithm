import kotlin.math.min
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    val t = readln().toInt()
    repeat(t) {
        val f = readln().toInt()
        val hm = HashMap<String, String>() // key의 부모는 value
        val cnt = HashMap<String, Int>()
        repeat(f) {
            val (a, b) = readln().split(" ")
            if(!hm.containsKey(a)) {
                hm[a] = a
                cnt[a] = 1
            }
            if(!hm.containsKey(b)) {
                hm[b] = b
                cnt[b] = 1
            }
            unionParent(hm, cnt, a, b)
            println(cnt[hm[a]]!!)
        }
    }
}

fun findParent(hm: HashMap<String, String>,cnt: HashMap<String, Int> ,x: String): String {
    if(hm[x] != x) {
        hm[x] = findParent(hm, cnt, hm[x]!!)
    }
    return hm[x]!!
}

fun unionParent(hm: HashMap<String, String>,cnt: HashMap<String, Int>, a: String, b: String) {
    val x = findParent(hm, cnt,a)
    val y = findParent(hm, cnt,b)
    if(x != y) {
        hm[y] = x
        cnt[x] = cnt[x]!! + cnt[y]!!
    }
}
