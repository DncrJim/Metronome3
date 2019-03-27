package com.dncrjim.metronome3


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TempoDao {

    @Query("SELECT * from tempoList")
    fun getAll(): List<Tempo>

    @Query("DELETE FROM tempoList")
    fun deleteAllTempos()


    @Query("select * from tempoList where id = :id")
    fun getTempoById(id: Int): Tempo

    @Query("DELETE from tempoList where id = :id")
    fun deleteTempoById(id: Int): Tempo

    // this may have an issue if a tempo from the middle is deleted
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTempo(tempo: Tempo)

//    @Update
//    fun updateTempo(tempoList: Tempo)
//
//
//    @Query("UPDATE Tempo SET tempo = :tempo, frequency = :frequency where id = :id")
//    fun updateTempo(tempo: Int, frequency: Int, id : Long?)

}
