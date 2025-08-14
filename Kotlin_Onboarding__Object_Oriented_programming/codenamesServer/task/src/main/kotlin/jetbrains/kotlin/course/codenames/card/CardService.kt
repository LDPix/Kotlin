package jetbrains.kotlin.course.codenames.card

import jetbrains.kotlin.course.codenames.utils.Utils
import jetbrains.kotlin.course.codenames.utils.words
import org.springframework.stereotype.Service

@Service
class CardService {

    fun generateWordsCards(): List<Card> {
        if (words.size < Utils.TOTAL_NUMBER) {
            throw IllegalStateException("")
        }
        val shuffled = words.shuffled()
        val selected = shuffled.take(Utils.TOTAL_NUMBER)
        val cards = selected.map { word ->
            Card(
                data = WordCardData(word),
                state = CardState.Front
            )
        }
        words = shuffled.drop(Utils.TOTAL_NUMBER)

        return cards
    }
}
