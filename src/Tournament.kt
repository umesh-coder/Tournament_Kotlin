
import java.util.*
import java.util.stream.Collectors
import kotlin.Comparator
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

internal class Tournament(tournamentType: TournamentType, matchFormat: MatchFormat) {
    private val tournamentType: TournamentType
    private val matchFormat: MatchFormat
    private val teams: MutableList<Team> = ArrayList()
    private val matches: MutableList<Match> = ArrayList()
    private val teamPoints: MutableMap<Team, Int> = HashMap()

    init {
        this.tournamentType = tournamentType
        this.matchFormat = matchFormat
    }

    fun addTeam(team: Team) {
        teams.add(team)
        teamPoints[team] = 0 // Initialize points
    }

    fun addMatch(match: Match) {

        matches.add(match)

        var winningTeam: Team = match.getWinningTeam()
        teamPoints[winningTeam] = teamPoints.getOrDefault(winningTeam, 0) + 2 // Assuming 2 points for a win
    }

    val semiFinalists: List<Team>
        get() = teams.stream()
            .sorted({ team1: Team, team2: Team ->
                teamPoints.get(team2)!!.compareTo((teamPoints.get(team1))!!)
            })
            .limit(2)
            .collect(Collectors.toList())

    val topRunScorers: List<Player>
        get() {
            return teams.stream()
                .flatMap({ team: Team ->
                    team.getPlayers().stream()
                })
                .sorted({ player1: Player, player2: Player -> player2.getStats().runsScored - player1.getStats().runsScored })
                .limit(5)
                .collect(Collectors.toList())
        }

    val tournamentWinner: Team
        get() {
            return Collections.max(teamPoints.entries, java.util.Map.Entry.comparingByValue()).key
        }

    val highestWicketTaker: Player?
        get() {
            return teams.stream()
                .flatMap({ team: Team ->
                    team.getPlayers().stream()
                })
                .max(Comparator.comparingInt({ player: Player? -> player!!.getStats().wicketsTaken }))
                .orElse(null)
        }

    fun getPlayerByName(name: String?): Player? {
        for (team: Team in teams) {
            for (player: Player in team.getPlayers()) {
                if (player.name.equals(name, ignoreCase = true)) {
                    return player
                }
            }
        }
        return null
    }


    fun getMatches(): List<Match> {
        return matches
    }

    fun displayScoreBoard() {
        println("Scoreboard:")
        println("-------------------------------------------------")
        System.out.printf(
            "%-20s %-7s %-20s %-15s %s\n",
            "Team 1",
            "Score",
            "Team 2",
            "Winner",
            "Team 1 : Team 2 Points"
        )
        println("-------------------------------------------------")
        for (match: Match in matches) {
            val winner: String = match.getWinningTeam().name
            val team1Points: Int = teamPoints.getOrDefault(match.team1, 0)
            val team2Points: Int = teamPoints.getOrDefault(match.team2, 0)
            System.out.printf(
                "%-20s %-7d %-20s %-15s %d : %d\n",
                match.team1.name, match.team1Runs,
                match.team2.name, winner, team1Points, team2Points
            )
        }
        println("-------------------------------------------------")


        // Display teams and their points in descending order
        println("Team Standings:")
        println("-------------------------------------------------")
        System.out.printf("%-20s %s\n", "Team", "Points")
        println("-------------------------------------------------")
        teamPoints.entries.stream()
            .sorted(java.util.Map.Entry.comparingByValue<Team, Int>().reversed())
            .forEach({ entry: Map.Entry<Team, Int> ->
                System.out.printf(
                    "%-20s %d\n",
                    entry.key.name,
                    entry.value
                )
            })
        println("-------------------------------------------------")
    }


}