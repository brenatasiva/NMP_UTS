package id.web.rpgfantasy.myapplication

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_result.*
import java.text.SimpleDateFormat
import java.util.*

class ResultActivity : AppCompatActivity() {
    companion object{
        var player1Name = ""
        var player2Name = ""
        var player1ColorId = 0
        var player2ColorId = 1
        var player1Status = "Draw"
        var player2Status = "Draw"
        var p1Name = ""
        var p2Name = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        GlobalData.session++

        player1Name = intent.getStringExtra("player1Name").toString()
        player2Name = intent.getStringExtra("player2Name").toString()
        player1ColorId = intent.getIntExtra("player1Color",0)
        player2ColorId = intent.getIntExtra("player2Color",1)
        player1Status = intent.getStringExtra("player1Status").toString()
        player2Status = intent.getStringExtra("player2Status").toString()

        textViewResPlayer1Name.text = player1Name + " (O)"
        textViewResPlayer2Name.text = player2Name + " (X)"

        textViewResPlayer1Status.text = player1Status
        textViewResPlayer2Status.text = player2Status

        cardViewResPlayer1.setCardBackgroundColor(Color.parseColor(GlobalData.color[MainActivity.player1ColorId].hex))
        cardViewResPlayer2.setCardBackgroundColor(Color.parseColor(GlobalData.color[MainActivity.player2ColorId].hex))

        //save the match
        if(player1Status == "You Win!"){
            p1Name = "Player 1 (O): $player1Name WIN"
            p2Name = "Player 2 (X): $player2Name"
        }else if(player2Status == "You Win!"){
            p1Name = "Player 1 (O): $player1Name"
            p2Name = "Player 2 (X): $player2Name WIN"
        }else{
            p1Name = "Player 1 (O): $player1Name"
            p2Name = "Player 2 (X): $player2Name"
        }
        val sdf = SimpleDateFormat("dd MMMM yyyy hh:mm")
        val currentDate = sdf.format(Date())
        var history = History(GlobalData.session, currentDate, p1Name, p2Name, player1ColorId, player2ColorId)
        GlobalData.history.add(history)
        GlobalData.historyNumber++

        buttonPlayAgain.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(GlobalData.PLAYER_1_NAME, player1Name)
            intent.putExtra(GlobalData.PLAYER_2_NAME, player2Name)
            intent.putExtra(GlobalData.PLAYER_1_COLOR, player1ColorId)
            intent.putExtra(GlobalData.PLAYER_2_COLOR, player2ColorId)
            startActivity(intent)
            finish()
        }

        buttonHistory.setOnClickListener{
            GlobalData.reverse()
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        val intent = Intent(this, PlayersActivity::class.java)
        startActivity(intent)
        finish()
    }
}