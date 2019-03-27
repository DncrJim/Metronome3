package com.dncrjim.metronome3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {

    lateinit var db: TempoDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var tempoAsInt: Int = intent.getIntExtra("currentTempo", 0)

        //build database
        //Todo: Trade these after testing
        db = Room.inMemoryDatabaseBuilder(this, TempoDatabase::class.java).allowMainThreadQueries().build()
        //db = Room.databaseBuilder(this, TempoDatabase::class.java, "TempoDatabase").build()

        //onClick for new tempo
        newTempoButton.setOnClickListener {
            val dialogBuilder = AlertDialog.Builder(this)
            val inflater = this.layoutInflater
            val dialogView = inflater.inflate(R.layout.custom_dialog, null)
            dialogBuilder.setView(dialogView)


            val editText = dialogView.findViewById<View>(R.id.editTempoText) as EditText
            dialogBuilder.setTitle("Add New Tempo")
            dialogBuilder.setPositiveButton("OK") {dialog, whichButton ->

                tempoAsInt = editText.text.toString().toInt()
                when (tempoAsInt) {
                    //reject tempoList outside of acceptable range
                    !in 36..300 -> Toast . makeText (this, "Error: Tempo must be between 36 and 300", Toast.LENGTH_LONG).show()
                    else -> {

                        //Todo: Unmark this to insert tempo into database
                        //db.tempoDao().insertTempo(Tempo(nextId(), tempoAsInt, 0))

                        //move to other activity and send new tempo
                        val intent = Intent(this, TempoActivity::class.java)
                        intent.putExtra("currentTempo", tempoAsInt)
                        startActivity(intent)
                    }
                    //Todo: also close dialog box?
            }
        }
        dialogBuilder.setNegativeButton("Cancel") {dialog, whichButton -> }

        val b = dialogBuilder.create()
        b.show()
        }










        //onClick for last tempo
        //Set GONE if no value for 'last tempo'
        if (tempoAsInt == 0) {
            lastTempoButton.visibility = View.GONE
        } else {
            lastTempoButton.visibility = View.VISIBLE
        }

        //Actual Button
        lastTempoButton.setOnClickListener {
            val intent = Intent(this, TempoActivity::class.java)
            intent.putExtra("currentTempo", tempoAsInt)
            startActivity(intent)
        }


    }
}
