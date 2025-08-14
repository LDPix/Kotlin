package jetbrains.kotlin.course.words.generator.team

import org.springframework.stereotype.Service

@Service
class TeamService {

    companion object {
        val teamsStorage: MutableMap<Identifier, Team> = mutableMapOf()
    }
    fun generateTeamsForOneRound(teamsNumber: Int): List<Team> {
        val newTeams = List(teamsNumber) { Team() }
        newTeams.forEach { team ->
            teamsStorage[team.id] = team
        }
        return newTeams
    }
}
