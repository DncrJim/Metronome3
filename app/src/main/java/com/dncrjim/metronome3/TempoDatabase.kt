package com.dncrjim.metronome3

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Tempo::class], version =1)
abstract class TempoDatabase : RoomDatabase() {
    abstract fun tempoDao(): TempoDao

}