package com.example.smartnotes

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable // Add this for passing the object between activities

@Entity(tableName = "notes_table")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val content: String,
    val userId: String,
    val timestamp: Long,
    var isSynced: Boolean = false

) : Serializable
