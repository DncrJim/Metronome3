package com.dncrjim.metronome3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.dncrjim.metronome3.data.TempoDatabase


class TempoActivity : AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tempo)

        //Todo: Remove .allowMainThreadQueries()
        db = Room.databaseBuilder(this, TempoDatabase::class.java, "TempoDatabase").allowMainThreadQueries().build()
        var tempoAsInt = 0

        val tempoId = intent.getIntExtra("tempoId", -1)
        if (tempoId != -1) {
            val tempo = db.tempoDao().getTempoById(tempoId)
            tempoAsInt = tempo.tempo
        }


        val tempoText: TextView = findViewById(R.id.tempoText)
        tempoText.setText(Integer.toString(tempoAsInt))


        //intent to go back to MainActivity passing currentTempo
        val button = findViewById<Button>(R.id.returnButton)
        button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("tempoId", tempoId)
            startActivity(intent)

        }
    }
}