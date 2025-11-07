package com.example.smartnotes

import androidx.lifecycle.LiveData

/**
 * The repository acts as a mediator between the ViewModel and the data source (DAO).
 * It provides a clean API for data access to the rest of the application.
 *
 * It had incorrect imports from androidx.compose. Those have been removed.
 */
class NoteRepository(private val noteDao: NoteDao) {

    // Gets LiveData from the DAO. The ViewModel observes this.
    fun getNotesForUser(userId: String): LiveData<List<Note>> {
        return noteDao.getNotesForUser(userId)
    }

    // Calls the suspend function in the DAO to insert a note.
    suspend fun insert(note: Note) {
        noteDao.insert(note)
    }

    // Calls the suspend function in the DAO to update a note.
    suspend fun update(note: Note) {
        noteDao.update(note)
    }

    // Calls the suspend function in the DAO to delete a note.
    suspend fun delete(note: Note) {
        noteDao.delete(note)
    }
}
