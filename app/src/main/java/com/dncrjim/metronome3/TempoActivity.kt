package com.dncrjim.metronome3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.*
import java.lang.reflect.Array.getChar

class TempoActivity : AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tempo)

        val tempoAsInt:Int = intent.getIntExtra("currentTempo" , 0)

        var tempoText: TextView = findViewById(R.id.tempoText)
        tempoText.setText(Integer.toString(tempoAsInt))

        val button = findViewById<Button>(R.id.returnButton)
        button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("currentTempo", tempoAsInt)
            startActivity(intent)

        }
    }
}