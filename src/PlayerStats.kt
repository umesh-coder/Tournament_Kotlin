internal class PlayerStats {
    var runsScored: Int = 0
        private set
    var wicketsTaken: Int = 0
        private set
    var halfCenturies: Int = 0
        private set

    fun updateStats(runs: Int, wickets: Int) {
        this.runsScored += runs
        this.wicketsTaken += wickets
        if (runs > 50) {
            halfCenturies++
        }
    }
}