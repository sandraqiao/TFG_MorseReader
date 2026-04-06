package com.example.morsereader.morse

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface MorseDao{
    @Query("SELECT * FROM morseMessages")
    fun getAll(): List<MorseMessage>

    @Insert
    suspend fun insert(message: MorseMessage)

    @Delete
    suspend fun delete(message: MorseMessage)

    @Update
    suspend fun update(message: MorseMessage)
}