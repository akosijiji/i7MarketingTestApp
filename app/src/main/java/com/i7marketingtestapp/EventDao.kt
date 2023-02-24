package com.i7marketingtestapp

import androidx.room.*
import com.i7marketingtestapp.model.Event
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addEvent(event: Event)

    @Query("SELECT * FROM events ORDER BY dateAdded DESC")
    fun getEvents(): Flow<List<Event>>

    @Update
    suspend fun updateEvent(note: Event)

    @Delete
    suspend fun deleteEvent(note: Event)
}