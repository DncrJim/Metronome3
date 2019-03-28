package com.dncrjim.metronome3

import androidx.room.Entity
import androidx.room.PrimaryKey

// this is the model for a tempo object

@Entity(tableName = "tempoList")
data class Tempo (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val tempo: Int? = null,
    val frequency: Int? = null
)
