package com.example.morsereader.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.morsereader.morse.MorseMessage
import kotlinx.coroutines.flow.Flow

@Dao
interface MorseDao{
    @Query("SELECT * FROM morseMessages")
    fun getAll(): Flow<List<MorseMessage>>

    @Insert
    suspend fun insert(message: MorseMessage)

    @Delete
    suspend fun delete(message: MorseMessage)

    @Update
    suspend fun update(message: MorseMessage)
}