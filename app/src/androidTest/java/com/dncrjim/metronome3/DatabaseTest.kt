package com.dncrjim.metronome3

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dncrjim.metronome3.data.Tempo
import com.dncrjim.metronome3.data.TempoDatabase

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class DatabaseTest {

    val testTempo = Tempo(0, 120, 0)

    @Test
    fun insertTest() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val db = Room.databaseBuilder(context, TempoDatabase::class.java, "tempoList").build()

        db.tempoDao().insertTempo(testTempo)
        val returnTempo = db.tempoDao().getTempoById(testTempo.id)
        assertEquals(testTempo, returnTempo)


    }


}