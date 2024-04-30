internal class Match(
    val team1: Team,
    val team2: Team,
    val team1Runs: Int,
    val team2Runs: Int
) {

    // Method to get the winning team
   public fun getWinningTeam(): Team = if (team1Runs > team2Runs) team1 else team2
}