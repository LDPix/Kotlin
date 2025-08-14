package jetbrains.kotlin.course.codenames.keyCard

import jetbrains.kotlin.course.codenames.utils.Utils
import org.springframework.stereotype.Service

data class KeyCard(
    val cells: List<KeyCardCell> = Utils.uniqueKeyCardGenerator.generateData()
)

@Service
class KeyCardService {
    fun generateKeyCard(): KeyCard = KeyCard()
}
