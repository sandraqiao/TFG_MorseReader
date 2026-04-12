package com.example.morsereader.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.morsereader.morse.MorseMessage

@Database(entities = [MorseMessage::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun morseDao(): MorseDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "morse_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}