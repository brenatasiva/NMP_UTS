package id.web.rpgfantasy.myapplication


object GlobalData {
    var historyNumber = 0
    val PLAYER_1_COLOR = "player1Color"
    val PLAYER_2_COLOR = "player2Color"
    val PLAYER_1_NAME = "player1Name"
    val PLAYER_2_NAME = "player2Name"
    val PLAYER_1_STATUS = "player1Status"
    val PLAYER_2_STATUS = "player2Status"
    var session = 0
    val color = arrayOf(
        Color(1, "Red", "#FF0000"),
        Color(2, "Green", "#00FF00"),
        Color(3, "Blue", "#0000FF")
    )

    val history = arrayListOf<History>()
    val reversedHistory = arrayListOf<History>()

    fun reverse(){
        reversedHistory.clear()
        var size = history.size - 1
        for (i in size downTo 0) {
            reversedHistory.add(history.get(i))
        }
    }
}