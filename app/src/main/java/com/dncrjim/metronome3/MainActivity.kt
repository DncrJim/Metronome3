package com.dncrjim.metronome3

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.dncrjim.metronome3.data.Tempo
import com.dncrjim.metronome3.data.TempoDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

lateinit var db: TempoDatabase

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {

        //build database
        //Todo: Remove .allowMainThreadQueries()
        db = Room.databaseBuilder(this, TempoDatabase::class.java, "TempoDatabase").allowMainThreadQueries().build()

        thread {
            db.tempoDao().insertTempo(Tempo(1, 120, 100))
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //increment frequency counter if tempo.id is called
        fun freqIncrement(tempoId: Int?) {
            if (tempoId != null) {
                val tempo = db.tempoDao().getTempoById(tempoId)
                val bpm = tempo.tempo
                val newFreq = tempo.frequency + 1
                db.tempoDao().updateTempo(tempoId, bpm, newFreq)
            }
        }



        //init recycler view

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        //click listener for frames inside recyclerview
        val launchTempoActivity = { id: Int? ->

            //increment frequency of Tempo clicked by +1
            freqIncrement(id)

            val intent = Intent(this, TempoActivity::class.java)
            intent.putExtra("tempoId", id)
            startActivity(intent)
        }

        val myAdapter = RecyclerViewAdapter(launchTempoActivity)
        recyclerView.adapter = myAdapter




        //onClick for new tempo
        newTempoButton.setOnClickListener {
            val dialogBuilder = AlertDialog.Builder(this)
            val inflater = this.layoutInflater
            val dialogView = inflater.inflate(R.layout.custom_dialog, null)
            dialogBuilder.setView(dialogView)


            val editText = dialogView.findViewById<View>(R.id.editTempoText) as EditText
            dialogBuilder.setTitle("Add New Tempo")
            dialogBuilder.setPositiveButton("OK") { dialog, whichButton ->

                // check to make sure AlertDialog text field is not empty
                if (editText.text.isNotEmpty()) {

                    val tempoAsInt = editText.text.toString().toInt()

                    //reject tempoList outside of acceptable range
                    when (tempoAsInt) {
                        !in 36..300 -> Toast.makeText(this,"Error: Tempo must be between 36 and 300",Toast.LENGTH_LONG).show()
                        else -> {

                            //get Id if existing tempo
                            val exists = db.tempoDao().getCountBytempo(tempoAsInt)
                            val tempoId = if (exists != 0) {

                                //Update if existing tempo
                                val OldTempo = db.tempoDao().getIDbytempo(tempoAsInt)
                                OldTempo.id!!
                            } else {

                                //Create new if not existing tempo
                                val newTempo = Tempo(null, tempoAsInt, 0)  //create new Tempo if none existing
                                db.tempoDao().insertTempo(newTempo).toInt()
                            }

                            //increment, move to other activity, and send tempo to TempoActivity
                            freqIncrement(tempoId)
                            val intent = Intent(this, TempoActivity::class.java)
                            intent.putExtra("tempoId", tempoId)
                            startActivity(intent)

                        }
                    }
                }
            }
            dialogBuilder.setNegativeButton("Cancel") {dialog, whichButton ->

            }

        val b = dialogBuilder.create()
        b.show()
        }

        //last tempo button

        //Init variable for current tempo when returning from TempoActivity
        val tempoId: Int = intent.getIntExtra("tempoId", 0)

        //onClick for last tempo
        when (tempoId) {
            0 -> lastTempoButton.visibility = View.GONE
            else -> lastTempoButton.visibility = View.VISIBLE
        }

        //Actual Button
        lastTempoButton.setOnClickListener {
            val intent = Intent(this, TempoActivity::class.java)
            intent.putExtra("tempoId", tempoId)
            startActivity(intent)
        }




    }
}
