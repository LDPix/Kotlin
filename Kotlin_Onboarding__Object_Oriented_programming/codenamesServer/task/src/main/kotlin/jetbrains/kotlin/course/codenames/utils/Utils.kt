package jetbrains.kotlin.course.codenames.utils

import jetbrains.kotlin.course.codenames.keyCard.KeyCardCell
import jetbrains.kotlin.course.codenames.keyCard.KeyCardType

object Utils {
    private const val N = 5
    const val TOTAL_NUMBER = N * N
    const val PINK_CARDS_NUMBER = 8
    const val VIOLET_CARDS_NUMBER = 9
    const val GRAY_CARDS_NUMBER = 7
    const val BLACK_CARDS_NUMBER = 1

    private val previousAttempts: MutableList<List<KeyCardCell>> = mutableListOf()

    val uniqueKeyCardGenerator: KeyCardGenerator = KeyCardGenerator {
        var newKeyCard: List<KeyCardCell>

        do {
            // Step 1: Create the list with correct counts
            newKeyCard = buildList {
                addAll(List(PINK_CARDS_NUMBER) { KeyCardCell(KeyCardType.Pink) })
                addAll(List(VIOLET_CARDS_NUMBER) { KeyCardCell(KeyCardType.Violet) })
                addAll(List(GRAY_CARDS_NUMBER) { KeyCardCell(KeyCardType.Gray) })
                addAll(List(BLACK_CARDS_NUMBER) { KeyCardCell(KeyCardType.Black) })
                shuffle() // Step 2: Shuffle the list
            }
            // Step 3: Repeat if this combination was already used
        } while (previousAttempts.contains(newKeyCard))

        // Step 4: Store and return
        previousAttempts.add(newKeyCard)
        newKeyCard
    }

    init {
        val sum = PINK_CARDS_NUMBER + VIOLET_CARDS_NUMBER + GRAY_CARDS_NUMBER + BLACK_CARDS_NUMBER
        if (sum != TOTAL_NUMBER) {
            throw IllegalArgumentException(
                "Card counts ($sum) do not match total number of cards ($TOTAL_NUMBER)"
            )
        }
    }
}

fun interface KeyCardGenerator {
    fun generateData(): List<KeyCardCell>
}