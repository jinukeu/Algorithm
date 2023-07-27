import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList
import java.util.PriorityQueue
import java.util.Queue
import kotlin.math.max
import kotlin.math.min

val br = BufferedReader(InputStreamReader(System.`in`))
val bw = BufferedWriter(OutputStreamWriter(System.out))

val red = Dot(0, 0)
val blue = Dot(0, 0)
val graph = mutableListOf<MutableList<String>>()

fun main() {
    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    repeat(n) {
        graph.add(br.readLine().split("").filter { it != "" }.toMutableList())
    }

    for(y in 0 until n)
        for(x in 0 until m) {
            if(graph[y][x] == "B") {
                blue.x = x
                blue.y = y
            }

            if(graph[y][x] == "R") {
                red.x = x
                red.y = y
            }
        }

    val queue = ArrayDeque<Info>()
    queue.add(Info(red.copy(), blue.copy(), 0))

    while (queue.isNotEmpty()) {
        val (cR, cB, cnt) = queue.removeFirst()

        if(cnt >= 10) continue

        repeat(4) {

            red.x = cR.x
            red.y = cR.y

            blue.x = cB.x
            blue.y = cB.y

            val result = when(it) {
                0 -> right()
                1 -> left()
                2 -> up()
                else -> down()
            }

            when(result) {
                true -> {
                    println(cnt + 1)
                    return
                }

                false -> {
                    queue.add(Info(red.copy(), blue.copy(), cnt + 1))
                }

                else -> {}
            }
        }
    }

    println(-1)
}

data class Info(
    val red: Dot,
    val blue: Dot,
    val count: Int
)

fun right(): Boolean? {
    return if(blue.y == red.y && blue.x > red.x) moveFirstB((1 to 0))
    else moveFirstA((1 to 0))
}

fun left(): Boolean? {
    return if(blue.y == red.y && blue.x < red.x) moveFirstB((-1 to 0))
    else moveFirstA((-1 to 0))
}

fun up(): Boolean? {
    return if(blue.x == red.x && blue.y < red.y) moveFirstB((0 to -1))
    else moveFirstA((0 to -1))
}

fun down(): Boolean? {
    return if(blue.x == red.x && blue.y > red.y) moveFirstB((0 to 1))
    else moveFirstA((0 to 1))
}

// null B가 먼저 탈출함
// false 이동 성공
// true 탈출 성공
fun moveFirstB(pair: Pair<Int, Int>): Boolean? {
    val (dx, dy) = pair

    // BLUE 이동
    while (true) {
        blue.x += dx
        blue.y += dy

        if(graph[blue.y][blue.x] == "#") {
            blue.x -= dx
            blue.y -= dy
            graph[blue.y][blue.x] = "B"
            break
        }

        if(graph[blue.y][blue.x] == "O") {
            return null
        }
    }

    // RED 이동

    while (true) {
        red.x += dx
        red.y += dy

        if(graph[red.y][red.x] == "#" || (red.y to red.x) == (blue.y to blue.x)) {
            red.x -= dx
            red.y -= dy
            graph[red.y][red.x] = "R"
            return false
        }

        if(graph[red.y][red.x] == "O") {
            return true
        }
    }

}

// null B가 먼저 탈출함
// false 이동 성공
// true 탈출 성공
fun moveFirstA(pair: Pair<Int, Int>): Boolean? {
    val (dx, dy) = pair
    // RED 이동

    var redOut = false

    while (true) {
        red.x += dx
        red.y += dy

        if(graph[red.y][red.x] == "#") {
            red.x -= dx
            red.y -= dy
            graph[red.y][red.x] = "R"
            break
        }

        if(graph[red.y][red.x] == "O") {
            redOut = true
            break
        }
    }

    // BLUE 이동
    if(!redOut) {
        while (true) {
            blue.x += dx
            blue.y += dy

            if(graph[blue.y][blue.x] == "#" || (red.y to red.x) == (blue.y to blue.x)) {
                blue.x -= dx
                blue.y -= dy
                graph[blue.y][blue.x] = "B"
                return false
            }

            if(graph[blue.y][blue.x] == "O") {
                return null
            }
        }
    } else {
        while (true) {
            blue.x += dx
            blue.y += dy

            if(graph[blue.y][blue.x] == "#") {
                blue.x -= dx
                blue.y -= dy
                graph[blue.y][blue.x] = "B"
                return true
            }

            if(graph[blue.y][blue.x] == "O") {
                return null
            }
        }
    }

}

data class Dot(
    var x: Int,
    var y: Int
)