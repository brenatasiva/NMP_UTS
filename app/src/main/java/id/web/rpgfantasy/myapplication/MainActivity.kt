package id.web.rpgfantasy.myapplication

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object{
        var btnDisabled = 0
        var turn = "O"
        var player1Name = ""
        var player2Name = ""
        var player1ColorId = 0
        var player2ColorId = 1
        var player1Status = "Draw"
        var player2Status = "Draw"
        var iswin = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnDisabled = 0
        turn = "O"
        //get players datas from intent
        player1Name = intent.getStringExtra("player1Name").toString()
        player2Name = intent.getStringExtra("player2Name").toString()
        player1ColorId = intent.getIntExtra("player1Color",0)
        player2ColorId = intent.getIntExtra("player2Color",1)

        textViewPlayer1Name.text = player1Name + " (O)"
        textViewPlayer2Name.text = player2Name + " (X)"

        textViewPlayer1Turn.text = "Your Turn!"
        textViewPlayer2Turn.text = player1Name + "'s Turn"

        cardViewMainPlayer1.setCardBackgroundColor(Color.parseColor(GlobalData.color[player1ColorId].hex))
        cardViewMainPlayer2.setCardBackgroundColor(Color.parseColor(GlobalData.color[player2ColorId].hex))
    }

    //Handler for button onclick
    fun buttonHandler(v: View) {
        if(v is Button){
            if(v.id == R.id.button00 || v.id == R.id.button01 || v.id == R.id.button02 ||
                v.id == R.id.button10 || v.id == R.id.button11 || v.id == R.id.button12 ||
                v.id == R.id.button20 || v.id == R.id.button21 || v.id == R.id.button22){
                v.text = turn
                btnDisabled++

                iswin = checkWinner(turn)
                if(iswin){
                    endGame(turn)
                }

                if(turn == "O"){
                    turn = "X"
                    textViewPlayer1Turn.text = player2Name + "'s Turn"
                    textViewPlayer2Turn.text = "Your Turn!"
                }else{
                    turn = "O"
                    textViewPlayer1Turn.text = "Your Turn!"
                    textViewPlayer2Turn.text = player1Name + "'s Turn"
                }
                v.isEnabled = false
            }
        }
    }

    override fun onBackPressed() {
        val alert = AlertDialog.Builder(this)
        alert.setTitle("QUIT GAME")
        alert.setMessage("Are you sure to quit this game?")
        alert.setPositiveButton("QUIT") { _,_ ->
            val intent = Intent(this@MainActivity,PlayersActivity::class.java)
            startActivity(intent)
            finish()
        }
        alert.setNegativeButton("Keep Playing") { _,_->
        }
        alert.show()
    }

    fun checkWinner(symbol:String):Boolean{
        //horizontal
        if(button00.text == symbol && button01.text == symbol && button02.text == symbol)
            return true
        if (button10.text == symbol && button11.text == symbol && button12.text == symbol)
            return true
        if (button20.text == symbol && button21.text == symbol && button22.text == symbol)
            return true

        //vertical
        if (button00.text == symbol && button10.text == symbol && button20.text == symbol)
            return true
        if (button01.text == symbol && button11.text == symbol && button21.text == symbol)
            return true
        if (button02.text == symbol && button12.text == symbol && button22.text == symbol)
            return true

        //diagonal
        if (button00.text == symbol && button11.text == symbol && button22.text == symbol)
            return true
        if (button02.text == symbol && button11.text == symbol && button20.text == symbol)
            return true

        if (btnDisabled == 9) {
            turn = "XO"
            return true
        }

        return false
    }

    fun endGame(tanda:String){
        var txt = "HOORAY!"
        val alert = AlertDialog.Builder(this)
        alert.setCancelable(false)
        alert.setTitle("GAME OVER")
        if(tanda == "O"){
            player1Status = "You Win!"
            player2Status = "You Lose!"
            alert.setMessage(player1Name + " wins!")
        }else if(tanda == "X"){
            player1Status = "You Lose!"
            player2Status = "You Win!"
            alert.setMessage(player2Name + " wins!")
        }else{
            player1Status = "Draw!"
            player2Status = "Draw!"
            alert.setMessage("Game draw!")
            txt = "OH NO..."
        }
        alert.setPositiveButton(txt) { _,_ ->
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra(GlobalData.PLAYER_1_NAME, player1Name)
            intent.putExtra(GlobalData.PLAYER_2_NAME, player2Name)
            intent.putExtra(GlobalData.PLAYER_1_COLOR, player1ColorId)
            intent.putExtra(GlobalData.PLAYER_2_COLOR, player2ColorId)
            intent.putExtra(GlobalData.PLAYER_1_STATUS, player1Status)
            intent.putExtra(GlobalData.PLAYER_2_STATUS, player2Status)
            startActivity(intent)
            finish()
        }
        alert.show()
    }
}