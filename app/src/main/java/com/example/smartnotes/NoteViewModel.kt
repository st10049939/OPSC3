package com.example.smartnotes

import androidx.lifecycle.*
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class NoteViewModel(private val repository: NoteRepository) : ViewModel() {

    // This LiveData will hold the notes for the current user.
    // The import for 'Note' is now correct.
    private val _userNotes = MediatorLiveData<List<Note>>()
    val userNotes: LiveData<List<Note>> = _userNotes

    private val auth = FirebaseAuth.getInstance()
    private var currentNotesSource: LiveData<List<Note>>? = null

    /**
     * Fetches notes from the repository for the currently logged-in user.
     * It properly handles user changes by removing the old source.
     */
    fun loadNotesForCurrentUser() {
        val userId = auth.currentUser?.uid ?: return

        // If there's an old source, remove it to prevent getting updates from a previous user.
        currentNotesSource?.let {
            _userNotes.removeSource(it)
        }

        // Get the new source and add it.
        val newSource = repository.getNotesForUser(userId)
        currentNotesSource = newSource
        _userNotes.addSource(newSource) { notes ->
            _userNotes.value = notes
        }
    }

    // Functions to modify data, called from the UI.
    fun insert(note: Note) = viewModelScope.launch {
        repository.insert(note)
    }

    fun update(note: Note) = viewModelScope.launch {
        repository.update(note)
    }

    fun delete(note: Note) = viewModelScope.launch {
        repository.delete(note)
    }
}
