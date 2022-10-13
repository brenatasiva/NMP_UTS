package id.web.rpgfantasy.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_history.*

class HistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        //setup recycler view using adapter
        val lm = LinearLayoutManager(this).apply{
            orientation = LinearLayoutManager.VERTICAL
        }

        with(recyclerView){
            layoutManager = lm
            setHasFixedSize(true)
            adapter = PSAdater(this@HistoryActivity)
        }
    }
}