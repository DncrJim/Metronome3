package com.dncrjim.metronome3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var tempoAsInt: Int = intent.getIntExtra("currentTempo", 0)


        //onClick for new tempo
        newTempoButton.setOnClickListener {
//            inputNewTempo()
        }

        //onClick for last tempo
        //Todo: if last tempo has not been defined when mainactivity generated, need to hide 'last tempo' button
        lastTempoButton.setOnClickListener {
            val intent = Intent(this, TempoActivity::class.java)
            intent.putExtra("currentTempo", tempoAsInt)
            startActivity(intent)
        }


    }
}
