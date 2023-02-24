package com.i7marketingtestapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "events")
data class Event(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "eventName")
    val eventText: String,
    @ColumnInfo(name = "dateAdded")
    val dateAdded: Date
)