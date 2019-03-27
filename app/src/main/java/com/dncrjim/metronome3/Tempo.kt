package com.dncrjim.metronome3

import androidx.room.Entity
import androidx.room.PrimaryKey

// this is the model for a tempo object

@Entity(tableName = "tempoList")
data class Tempo (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val tempo: Int,
    val frequency: Int
)