import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.PriorityQueue
import kotlin.math.max
import kotlin.math.min

val br = BufferedReader(InputStreamReader(System.`in`))
val bw = BufferedWriter(OutputStreamWriter(System.out))
val robots = mutableListOf<Robot>(Robot(-1, -1, ""))

fun main() {
    val (a, b) = br.readLine().split(" ").map { it.toInt() }
    val (n, m) = br.readLine().split(" ").map { it.toInt() }

    repeat(n) {
        val input = br.readLine().split(" ")
        val dir = input[2]
        robots.add(
            Robot(
                input[0].toInt() - 1,
                b - input[1].toInt(),
                dir
            )
        )
    }

    repeat(m) {
        val commands = br.readLine().split(" ")
        var robotNum = commands[0].toInt()
        var command = commands[1]
        var rep = commands[2].toInt()

        when(command) {
            "F" -> {
                if(!doF(robotNum, robots[robotNum], rep, a, b))
                    return
            }
            "L" -> {
                repeat(rep) {
                    robots[robotNum].dir = turnLeft(robots[robotNum].dir)
                }
            }
            else -> {
                repeat(rep) {
                    robots[robotNum].dir = turnRight(robots[robotNum].dir)
                }
            } // R
        }
    }

    println("OK")
}

fun doF(robotNum: Int, robot: Robot, rep: Int, a: Int, b: Int): Boolean {
    val (dx, dy) = getFXY(robot.dir)
    repeat(rep) {
        robot.x += dx
        robot.y += dy

        if(robot.x in 0 until a && robot.y in 0 until b) {
            for(idx in robots.indices) {
                if(idx == robotNum) continue
                if(robots[idx].x == robot.x && robots[idx].y == robot.y) {
                    println("Robot $robotNum crashes into robot $idx")
                    return false
                }
            }

        } else {
            println("Robot $robotNum crashes into the wall")
            return false
        }
    }

    return true
}

fun getFXY(dir: String): Pair<Int, Int> {
    return when(dir) {
        "N" -> (0 to -1)
        "S" -> (0 to 1)
        "W" -> (-1 to 0)
        else -> (1 to 0)
    }
}

fun turnLeft(dir: String): String {
    return when(dir) {
        "N" -> "W"
        "S" -> "E"
        "W" -> "S"
        else -> "N"
    }
}

fun turnRight(dir: String): String {
    return when(dir) {
        "N" -> "E"
        "S" -> "W"
        "W" -> "N"
        else -> "S"
    }
}

data class Robot(
    var x: Int,
    var y: Int,
    var dir: String,
)