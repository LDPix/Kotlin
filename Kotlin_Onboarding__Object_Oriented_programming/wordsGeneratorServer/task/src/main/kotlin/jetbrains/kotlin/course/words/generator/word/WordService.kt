package jetbrains.kotlin.course.words.generator.word

import jetbrains.kotlin.course.words.generator.util.words
import org.springframework.stereotype.Service

@Service
class WordService {

    companion object {
        val numberOfWords: Int = words.size
        val previousWords: MutableMap<String, MutableList<Word>> = mutableMapOf()
    }

    fun generateNextWord(): Word {
        if (words.isEmpty()) {
            error("No more words available!")
        }
        val next = words.first()
        words.removeAt(0)
        return Word(next)
    }

    fun isValidWord(keyWord: String, newWord: String): Boolean {
        if (newWord.isEmpty()) return false
        val keyCounts = keyWord.groupingBy { it }.eachCount()
        val newCounts = newWord.groupingBy { it }.eachCount()
        for ((char, count) in newCounts) {
            val maxAllowed = keyCounts[char] ?: return false
            if (count > maxAllowed) return false
        }
        return true
    }

    fun isNewWord(keyWord: String, newWord: String): Boolean {
        val wordObj = Word(newWord)
        val guessedList = previousWords.getOrPut(keyWord) { mutableListOf() }
        return if (guessedList.contains(wordObj)) {
            false
        } else {
            guessedList.add(wordObj)
            true
        }
    }
}
