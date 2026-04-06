package com.example.morsereader.morse

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "morseMessages")
data class MorseMessage(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val text: String
)