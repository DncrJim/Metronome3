package com.dncrjim.metronome3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class TempoActivity : AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tempo)

        val tempoAsInt:Int = intent.getIntExtra("currentTempo" , 0)


        val button = findViewById<Button>(R.id.returnButton)
        button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("currentTempo", tempoAsInt)
            startActivity(intent)

        }
    }
}