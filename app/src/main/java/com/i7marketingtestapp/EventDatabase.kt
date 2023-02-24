package com.i7marketingtestapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.i7marketingtestapp.model.Event

@Database(
    entities = [Event::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(EventConverters::class)
abstract class EventDatabase : RoomDatabase() {

    abstract fun eventDao(): EventDao

    companion object {

        @Volatile
        private var INSTANCE: EventDatabase? = null

        fun getDatabase(context: Context): EventDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            if (INSTANCE == null) {
                synchronized(this) {
                    // Pass the database to the INSTANCE
                    INSTANCE = buildDatabase(context)
                }
            }
            // Return database.
            return INSTANCE!!
        }

        private fun buildDatabase(context: Context): EventDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                EventDatabase::class.java,
                "events_database"
            )
                .build()
        }
    }
}