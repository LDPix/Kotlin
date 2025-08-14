package jetbrains.kotlin.course.card.trainer.card

import jetbrains.kotlin.course.card.trainer.util.countries
import org.springframework.stereotype.Service

@Service
class CardService {

    fun getNextCard(): Card {
        if (cards.isEmpty()) {
            throw NoSuchElementException("")
        }
        return cards.removeAt(0)
    }

    fun startNewGame(): Card {
        cards = generateNewCardsSequence()
        return getNextCard()
    }

    companion object {
        val randomCardGenerator: CardSequenceGenerator = object : CardSequenceGenerator {
            override fun generateCards(): List<Card> {
                return countries.map { (capital, country) ->
                    Card(Front(capital), Back(country))
                }.shuffled()
            }
        }

        private fun generateNewCardsSequence(): MutableList<Card> {
            return randomCardGenerator.generateCards().toMutableList()
        }

        var cards: MutableList<Card> = generateNewCardsSequence()
    }
}