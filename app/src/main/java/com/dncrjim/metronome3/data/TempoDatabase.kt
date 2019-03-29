package com.dncrjim.metronome3.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Tempo::class], version =1, exportSchema = false)
abstract class TempoDatabase : RoomDatabase() {
    abstract fun tempoDao(): TempoDao

}