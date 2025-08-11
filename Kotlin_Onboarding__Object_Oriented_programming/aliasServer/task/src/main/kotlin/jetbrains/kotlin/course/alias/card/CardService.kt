package jetbrains.kotlin.course.alias.card

import jetbrains.kotlin.course.alias.util.IdentifierFactory
import jetbrains.kotlin.course.alias.util.words
import org.springframework.stereotype.Service

@Service
class CardService(private val identifierFactory: IdentifierFactory = IdentifierFactory()) {

    private val cards: List<Card> = generateCards()
    companion object {
        const private val WORDS_IN_CARD = 4

        val cardsAmount: Int = words.size / WORDS_IN_CARD
    }
    private fun generateCards(): List<Card> {
        val shuffledWords = words.shuffled()
        return shuffledWords
            .chunked(WORDS_IN_CARD)
            .take(cardsAmount)
            .map { chunk ->
                Card(
                    id = identifierFactory.uniqueIdentifier(),
                    words = chunk.toWords()
                )
            }
    }

    private fun List<String>.toWords(): List<Word> =
        map { Word(it) }

    fun getCardByIndex(index: Int): Card {
        if (index !in cards.indices) {
            throw IllegalArgumentException("Card with index $index does not exist.")
        }
        return cards[index]
    }
}
