package jetbrains.kotlin.course.alias.results

import jetbrains.kotlin.course.alias.team.Team
import jetbrains.kotlin.course.alias.team.TeamService
import jetbrains.kotlin.course.alias.util.Identifier
import org.springframework.stereotype.Service

typealias GameResult = List<Team>

@Service
class GameResultsService {
    companion object {
        val gameHistory: MutableList<GameResult> = mutableListOf()
    }

    fun saveGameResults(result: GameResult) {
        if (result.isEmpty()) {
            throw IllegalArgumentException("Game result cannot be empty.")
        }
        val allTeamIds: Set<Identifier> = result.map { it.id }.toSet()
        val knownIds = TeamService.teamsStorage.keys
        if (!knownIds.containsAll(allTeamIds)) {
            throw IllegalArgumentException("Some team IDs in the result are not registered in TeamService.")
        }
        gameHistory.add(result)
    }

    fun getAllGameResults(): List<GameResult> =
        gameHistory.asReversed()
}
