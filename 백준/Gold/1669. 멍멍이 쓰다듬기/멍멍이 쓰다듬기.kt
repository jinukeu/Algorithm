import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.Collections
import kotlin.math.*


private val br = BufferedReader(InputStreamReader(System.`in`))

fun main(args: Array<String>) {
    val (x, y) = br.readLine().split(" ").map { it.toInt() }
    val c = (y - x).toLong()
    val answers = listOf((-1 + sqrt(abs(1 + (-4 * c)).toDouble()))/2, (-1 - sqrt(abs(1 + (-4 * c)).toDouble()))/2).filter { it >= 0 }
    val answer = Collections.min(answers)
    println(roundUpDigit(answer * 2, 0).toInt())
}

fun roundUpDigit(number : Double, digits : Int): Double {
    return Math.ceil(number * Math.pow(10.0, digits.toDouble())) / Math.pow(10.0, digits.toDouble())
}