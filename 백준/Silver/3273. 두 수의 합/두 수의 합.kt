fun main(args: Array<String>) {
    readln()
    val nums = readln().split(" ").map { it.toInt() }
    val sortedNums = nums.sorted()
    val x = readln().toInt()

    var startIdx = 0
    var endIdx = sortedNums.size - 1
    var count = 0

    while (startIdx != endIdx) {
        val sum = sortedNums[startIdx] + sortedNums[endIdx]

        when(sum) {
            x -> {
                count++
                startIdx++
            }
            in 0 until x -> startIdx++
            else -> endIdx--
        }
    }

    println(count)
}