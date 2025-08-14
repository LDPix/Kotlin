package jetbrains.kotlin.course.card.trainer.stat

import jetbrains.kotlin.course.card.trainer.card.Back
import org.springframework.stereotype.Service

@Service
class StatService {

    fun getHistory(): MutableList<Stat> =
        history.asReversed().toMutableList()

    fun save(knownBacks: List<String>, unknownBacks: List<String>) {
        val stat = Stat(
            knownBacks = knownBacks.map { Back(it) },
            unknownBacks = unknownBacks.map { Back(it) }
        )
        history.add(stat)
    }

    companion object {
        private val history: MutableList<Stat> = mutableListOf()
    }
}
