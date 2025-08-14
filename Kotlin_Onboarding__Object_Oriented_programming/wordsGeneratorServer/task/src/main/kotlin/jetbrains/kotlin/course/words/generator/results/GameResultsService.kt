package jetbrains.kotlin.course.words.generator.results

import jetbrains.kotlin.course.words.generator.team.Identifier
import jetbrains.kotlin.course.words.generator.team.Team
import jetbrains.kotlin.course.words.generator.team.TeamService
import org.springframework.stereotype.Service

typealias GameResult = List<Team>

@Service
class GameResultsService {

    companion object {
        val gameHistory: MutableList<GameResult> = mutableListOf()
    }

    fun saveGameResults(result: GameResult) {
        if (result.isEmpty()) {
            error("")
        }
        val allTeamIds: Set<Identifier> = TeamService.teamsStorage.keys.toSet()
        val resultIds: Set<Identifier> = result
            .map { team: Team -> team.id }
            .toSet()
        if (!allTeamIds.containsAll(resultIds)) {
            error(".")
        }

        gameHistory.add(result)
    }

    fun getAllGameResults(): List<GameResult> = gameHistory.asReversed()
}
