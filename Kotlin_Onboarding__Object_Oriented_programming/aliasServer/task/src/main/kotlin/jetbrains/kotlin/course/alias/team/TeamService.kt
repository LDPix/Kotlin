package jetbrains.kotlin.course.alias.team

import jetbrains.kotlin.course.alias.util.Identifier
import jetbrains.kotlin.course.alias.util.IdentifierFactory
import org.springframework.stereotype.Service

@Service
class TeamService(private val identifierFactory: IdentifierFactory = IdentifierFactory()) {

    companion object {
        val teamsStorage: MutableMap<Identifier, Team> = mutableMapOf()
    }
    fun generateTeamsForOneRound(teamsNumber: Int): List<Team> {
        val newTeams = List(teamsNumber) {
            val id = identifierFactory.uniqueIdentifier()
            Team(id = id)
        }
        newTeams.forEach { team ->
            teamsStorage[team.id] = team
        }
        return newTeams
    }
}
