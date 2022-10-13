package id.web.rpgfantasy.myapplication

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_players.*

class PlayersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_players)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, GlobalData.color)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerPlayer1.adapter = adapter
        spinnerPlayer2.adapter = adapter

        spinnerPlayer1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                cardViewPlayer1.setBackgroundColor(Color.parseColor(GlobalData.color[spinnerPlayer1.selectedItemPosition].hex))
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        spinnerPlayer2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                cardViewPlayer2.setBackgroundColor(Color.parseColor(GlobalData.color[spinnerPlayer2.selectedItemPosition].hex))
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        buttonPlay.setOnClickListener{
            val player1Name = if(editTextPlayer1Name.text.toString() == "") "Pentol" else editTextPlayer1Name.text.toString()
            val player2Name = if(editTextPlayer2Name.text.toString() == "") "Silang" else editTextPlayer2Name.text.toString()
            val player1ColorId = spinnerPlayer1.selectedItemPosition
            val player2ColorId = spinnerPlayer2.selectedItemPosition
            if(player1ColorId == player2ColorId){
                val alert = AlertDialog.Builder(this)
                alert.setTitle("ALERT!")
                alert.setMessage("Player 1 and Player 2 cannot have the same color")
                alert.setPositiveButton("OK") { _,_ ->}
                alert.show()
            }else{
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra(GlobalData.PLAYER_1_NAME, player1Name)
                intent.putExtra(GlobalData.PLAYER_2_NAME, player2Name)
                intent.putExtra(GlobalData.PLAYER_1_COLOR, player1ColorId)
                intent.putExtra(GlobalData.PLAYER_2_COLOR, player2ColorId)
                startActivity(intent)
                finish()
            }
        }
    }
}