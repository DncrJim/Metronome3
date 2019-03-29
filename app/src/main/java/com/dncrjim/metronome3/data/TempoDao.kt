package com.dncrjim.metronome3.data


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TempoDao {

    @Query("SELECT * FROM tempoList ORDER BY frequency DESC")
    fun getAllTempos(): List<Tempo>

    @Query("DELETE FROM tempoList")
    fun deleteAllTempos()


    @Query("SELECT * FROM tempoList WHERE id = :id")
    fun getTempoById(id: Int): Tempo

    @Query("Select * FROM tempoList WHERE tempo = :tempo LIMIT 1 ")
    fun getIDbytempo(tempo: Int): Tempo

    @Query("SELECT count(tempo) FROM tempoList WHERE tempo = :tempo")
    fun getCountBytempo(tempo: Int): Int

    // this may have an issue if a tempo from the middle is deleted
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTempo(tempo: Tempo): Long

    @Query("UPDATE tempoList SET tempo = :tempo, frequency = :frequency WHERE id = :id")
    fun updateTempo(id : Int, tempo: Int, frequency: Int)

//    @Query("DELETE from tempoList where id = :id")
//    fun deleteTempoById(id: Int): Tempo

}
