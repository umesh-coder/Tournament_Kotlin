
import java.util.stream.Collectors

object Maintest {
    @JvmStatic
    fun main(args: Array<String>) {
        try {
            val ipl = Tournament(TournamentType.IPL, MatchFormat.T20)
            val csk = Team("Chennai Super Kings", "M. A. Chidambaram Stadium")
            val mi = Team("Mumbai Indians", "Wankhede Stadium")
            val rr = Team("Rajasthan Royals", "Sawai Mansingh Stadium")
            val rcb = Team("Royal Challengers Bangalore", "M. Chinnaswamy Stadium")

            // Adding players to teams - Chennai Super Kings
            csk.addPlayer(Player("MS Dhoni", "Wicketkeeper", csk))
            csk.addPlayer(Player("Suresh Raina", "Batsman", csk))
            csk.addPlayer(Player("Ravindra Jadeja", "Allrounder", csk))
            csk.addPlayer(Player("Ambati Rayudu", "Batsman", csk))
            csk.addPlayer(Player("Deepak Chahar", "Bowler", csk))
            csk.addPlayer(Player("Imran Tahir", "Bowler", csk))
            csk.addPlayer(Player("Faf du Plessis", "Batsman", csk))
            csk.addPlayer(Player("Shardul Thakur", "Bowler", csk))
            csk.addPlayer(Player("Dwayne Bravo", "Allrounder", csk))
            csk.addPlayer(Player("Moeen Ali", "Allrounder", csk))
            csk.addPlayer(Player("Robin Uthappa", "Batsman", csk))
            csk.addPlayer(Player("Krishnappa Gowtham", "Allrounder", csk))

            // Adding players to teams - Mumbai Indians
            mi.addPlayer(Player("Rohit Sharma", "Batsman", mi))
            mi.addPlayer(Player("Kieron Pollard", "Allrounder", mi))
            mi.addPlayer(Player("Jasprit Bumrah", "Bowler", mi))
            mi.addPlayer(Player("Quinton de Kock", "Wicketkeeper", mi))
            mi.addPlayer(Player("Suryakumar Yadav", "Batsman", mi))
            mi.addPlayer(Player("Hardik Pandya", "Allrounder", mi))
            mi.addPlayer(Player("Krunal Pandya", "Allrounder", mi))
            mi.addPlayer(Player("Trent Boult", "Bowler", mi))
            mi.addPlayer(Player("Ishan Kishan", "Batsman", mi))
            mi.addPlayer(Player("Rahul Chahar", "Bowler", mi))
            mi.addPlayer(Player("Jayant Yadav", "Allrounder", mi))
            mi.addPlayer(Player("Anukul Roy", "Bowler", mi))

            // Adding players to teams - Rajasthan Royals
            rr.addPlayer(Player("Steve Smith", "Batsman", rr))
            rr.addPlayer(Player("Ben Stokes", "Allrounder", rr))
            rr.addPlayer(Player("Sanju Samson", "Wicketkeeper", rr))
            rr.addPlayer(Player("Jos Buttler", "Batsman", rr))
            rr.addPlayer(Player("Jofra Archer", "Allrounder", rr))
            rr.addPlayer(Player("Rahul Tewatia", "Allrounder", rr))
            rr.addPlayer(Player("Shreyas Gopal", "Bowler", rr))
            rr.addPlayer(Player("Andrew Tye", "Bowler", rr))
            rr.addPlayer(Player("Mayank Markande", "Bowler", rr))
            rr.addPlayer(Player("Manan Vohra", "Batsman", rr))
            rr.addPlayer(Player("Kartik Tyagi", "Bowler", rr))
            rr.addPlayer(Player("David Miller", "Batsman", rr))

            // Adding players to teams - Royal Challengers Bangalore
            rcb.addPlayer(Player("Virat Kohli", "Batsman", rcb))
            rcb.addPlayer(Player("AB de Villiers", "Batsman", rcb))
            rcb.addPlayer(Player("Glenn Maxwell", "Allrounder", rcb))
            rcb.addPlayer(Player("Mohammed Siraj", "Bowler", rcb))
            rcb.addPlayer(Player("Yuzvendra Chahal", "Bowler", rcb))
            rcb.addPlayer(Player("Devdutt Padikkal", "Batsman", rcb))
            rcb.addPlayer(Player("Daniel Sams", "Allrounder", rcb))
            rcb.addPlayer(Player("Washington Sundar", "Allrounder", rcb))
            rcb.addPlayer(Player("Navdeep Saini", "Bowler", rcb))
            rcb.addPlayer(Player("Kyle Jamieson", "Allrounder", rcb))
            rcb.addPlayer(Player("Sachin Baby", "Batsman", rcb))
            rcb.addPlayer(Player("Shahbaz Ahmed", "Allrounder", rcb))

            // Adding teams to the tournament
            ipl.addTeam(csk)
            ipl.addTeam(mi)
            ipl.addTeam(rr)
            ipl.addTeam(rcb)

            // Simulating matches
            var match1 = Match(csk, mi, 200, 180)
            var match2 = Match(rr, rcb, 180, 185)
            var match3 = Match(mi, csk, 190, 195)
            var match4 = Match(csk, rcb, 185, 190)
            var match5 = Match(rr, rcb, 120, 121)


            // Update player scores based on realistic match data
            updatePlayerScores(match1, csk, mi)
            updatePlayerScores(match2, rr, rcb)
            updatePlayerScores(match3, mi, csk)
            updatePlayerScores(match4, csk, rcb)
            updatePlayerScores(match5, rr, rcb)

            // Add matches to the tournament
            ipl.addMatch(match1)
            ipl.addMatch(match2)
            ipl.addMatch(match3)
            ipl.addMatch(match4)
            ipl.addMatch(match5)

            // Displaying the scoreboard
            ipl.displayScoreBoard()

            // Handling queries directly in main
            val highestWicketTaker = ipl.highestWicketTaker
            if (highestWicketTaker != null) {
                println(
                    """
Highest wicket taker: ${highestWicketTaker.name} from ${highestWicketTaker.getTeam().name} with ${highestWicketTaker.getStats().wicketsTaken} wickets"""
                )
            }

            val topRunScorers = ipl.topRunScorers
            if (!topRunScorers.isEmpty()) {
                val topRunScorer = topRunScorers[0]
                println(
                    """
Top run scorer: ${topRunScorer.name} from ${topRunScorer.getTeam().name} with ${topRunScorer.getStats().runsScored} runs"""
                )
            }

            // Finding the player with the most half-centuries
            val mostHalfCenturiesPlayer = ipl.topRunScorers.stream()
                .max(Comparator.comparingInt { player: Player -> player.getStats().halfCenturies })
                .orElse(null)
            if (mostHalfCenturiesPlayer != null) {
                println(
                    """
Player with most half-centuries: ${mostHalfCenturiesPlayer.name} from ${mostHalfCenturiesPlayer.getTeam().name} with ${mostHalfCenturiesPlayer.getStats().runsScored / 50} half-centuries"""
                )
            }

            println(
                """
                    
                    Semi Finalists: ${
                    ipl.semiFinalists.stream().map(Team::name).collect(Collectors.joining(", "))
                }
                    """.trimIndent()
            )

            println("\nTop Run Scorers: " +
                    ipl.topRunScorers.stream()
                        .map { player: Player -> player.name + " (" + player.getStats().runsScored + " runs)" }
                        .collect(Collectors.joining(", ")))
            println(
                """
                    
                    Tournament Winner: ${ipl.tournamentWinner.name}
                    """.trimIndent()
            )
        } catch (e: Exception) {
            println("Error: " + e.message)
        }
    }

    // Method to update player scores based on match data
    private fun updatePlayerScores(match: Match, team1: Team, team2: Team) {
        for (player in team1.getPlayers()) {
            if (match.getWinningTeam() == team1) {
                // Player belongs to the winning team
                if (player.role == "Batsman") {
                    player.updateStats((Math.random() * 100).toInt(), 0) // Random runs scored
                } else if (player.role == "Bowler") {
                    player.updateStats(0, (Math.random() * 5).toInt()) // Random wickets taken
                }
            }
        }
        for (player in team2.getPlayers()) {
            if (match.getWinningTeam() == team2) {
                // Player belongs to the winning team
                if (player.role == "Batsman") {
                    player.updateStats((Math.random() * 100).toInt(), 0) // Random runs scored
                } else if (player.role == "Bowler") {
                    player.updateStats(0, (Math.random() * 5).toInt()) // Random wickets taken
                }
            }
        }
    }
}