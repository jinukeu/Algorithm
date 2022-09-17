import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.Collections.max
import kotlin.contracts.contract

private val br = BufferedReader(InputStreamReader(System.`in`))

fun main(args: Array<String>) {
    val n = br.readLine().toInt()
    val commandList = mutableListOf<List<String>>()
    repeat(n) {
        commandList.add(br.readLine().split(" "))
    }
    val commandToBinary = hashMapOf(
        "ADD" to "00000", "ADDC" to "00001", "SUB" to "00010","SUBC" to "00011", "MOV" to "00100", "MOVC" to "00101", "AND" to "00110", "ANDC" to "00111",
        "OR" to "01000", "ORC" to "01001",
        "NOT" to "01010", "MULT" to "01100", "MULTC" to "01101", "LSFTL" to "01110", "LSFTLC" to "01111", "LSFTR" to "10000","LSFTRC" to "10001",
        "ASFTR" to "10010", "ASFTRC" to "10011",
        "RL" to "10100", "RLC" to "10101", "RR" to "10110", "RRC" to "10111"
    )

    commandList.forEach { command ->
        Integer.toBinaryString(command[1].toInt())

        var ans = commandToBinary[command[0]] + "0"
        ans += Integer.toBinaryString(command[1].toInt()).addZero(3) + Integer.toBinaryString(command[2].toInt()).addZero(3)

        ans += if(command[0].last() == 'C')  Integer.toBinaryString(command[3].toInt()).addZero(4)
        else Integer.toBinaryString(command[3].toInt()).addZero(3)

        if(ans.length != 16) ans += "0"

        println(ans)
    }
}

fun String.addZero(padding: Int): String {
    var formatStr = this
    repeat(padding - this.length) {
        formatStr = "0$formatStr"
    }
    return formatStr
}