import java.io.BufferedReader
import java.io.InputStreamReader

private val br = BufferedReader(InputStreamReader(System.`in`))

fun main(args: Array<String>) {
    val x = br.readLine().toInt()
    var word = br.readLine().split("")
    word = word.subList(1, word.size - 1)
    var testWord = word.toTypedArray()
    var wordArray = mutableListOf<Array<String>>()

    var cnt = 0
    while(true) {
        wordArray.add(testWord)
        cnt++
        val shakedString = Array(testWord.size) { "" }
        for (i in word.indices) {
            if (i % 2 == 0) shakedString[i / 2] = testWord[i]
            else shakedString[testWord.size - i / 2 - 1] = testWord[i]
        }
        testWord = shakedString
        if(shakedString.contentEquals(word.toTypedArray())) break
    }
    println(wordArray[x % cnt].joinToString(""))
}