internal class Team(val name: String, val homeGround: String) {
    private val players: MutableList<Player> = ArrayList()

    fun addPlayer(player: Player) {
        players.add(player)
        player.setTeam(this) // Use the setter method instead of direct access
    }

    fun getPlayers(): List<Player> {
        return players
    }

}