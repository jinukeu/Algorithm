import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.HashMap

private val br = BufferedReader(InputStreamReader(System.`in`))

fun main(args: Array<String>) {
    val trie = Trie()
    val n = br.readLine().toInt()
    repeat(n) {
        trie.insert(br.readLine().split(" ").drop(1))
    }
    trie.showElement(trie.root, 0)
}

class TrieNode(var key: String?, var parent: TrieNode?) {
    val children: TreeMap<String, TrieNode> = TreeMap()
    var isTerminating = false
}

class Trie {
    val root = TrieNode(null, null)

    fun insert(list: List<String>) {
        var current = root

        list.forEach { element ->
            if(current.children[element] == null) current.children[element] = TrieNode(element, current)
            current = current.children[element]!!
        }

        current.isTerminating = true
    }

    fun showElement(current: TrieNode, count: Int) {
        current.children.keys.forEach { key ->
            println(getDash(count) + key)
            showElement(current.children[key]!!, count + 1)
        }
    }
}

fun getDash(count: Int): String {
    val dash = "--"
    var returnDash = ""
    repeat(count) { returnDash += dash }
    return returnDash
}

