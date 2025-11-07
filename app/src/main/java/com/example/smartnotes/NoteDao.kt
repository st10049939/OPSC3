package com.example.smartnotes

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    // Gets all notes for a specific user, ordered by the most recent.
    @Query("SELECT * FROM notes_table WHERE userId = :userId ORDER BY timestamp DESC")
    fun getNotesForUser(userId: String): LiveData<List<Note>>

    // Inserts a note. If it already exists, it will be replaced.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    // Updates an existing note.
    @Update
    suspend fun update(note: Note)

    // Deletes a note.
    @Delete
    suspend fun delete(note: Note)
}

