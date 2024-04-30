internal class Player(val name: String, role: String, team: Team) {
    private val stats: PlayerStats
    val role: String
    private var team: Team // Reference to the Team object

    init {
        this.stats = PlayerStats()
        this.role = role
        this.team = team // Set the team reference
    }

    fun updateStats(runs: Int, wickets: Int) {
        stats.updateStats(runs, wickets)
    }

    fun getStats(): PlayerStats {
        return stats
    }

    fun getTeam(): Team {
        return team
    }

    fun setTeam(team: Team) {
        this.team = team
    }
}