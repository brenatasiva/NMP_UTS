package id.web.rpgfantasy.myapplication

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.history_card.view.*

class PSAdater(val context:Context):RecyclerView.Adapter<PSAdater.PSViewHolder>() {
    class PSViewHolder(val v: View):RecyclerView.ViewHolder(v)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PSViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var v = inflater.inflate(R.layout.history_card, parent, false)
        return PSViewHolder(v)
    }

    override fun onBindViewHolder(holder: PSViewHolder, position: Int) {
        val history = GlobalData.reversedHistory[position]
        with(holder.v){
            textViewGameSession.text = "Game Session #" + history.session
            textViewDateTime.text = history.datetime
            textViewHistoryPlayer1.text = history.player1Name
            textViewHistoryPlayer2.text = history.player2Name
            textViewHistoryPlayer1.setBackgroundColor(Color.parseColor(GlobalData.color[history.player1ColorId].hex))
            textViewHistoryPlayer2.setBackgroundColor(Color.parseColor(GlobalData.color[history.player2ColorId].hex))
        }
    }

    override fun getItemCount(): Int = GlobalData.history.size
}