package com.example.smartnotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * This factory is required to create an instance of NoteViewModel
 * because the ViewModel has a constructor that takes a NoteRepository.
 */
class NoteViewModelFactory(private val repository: NoteRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Check if the requested ViewModel class is NoteViewModel
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
            // If it is, create and return an instance of NoteViewModel
            @Suppress("UNCHECKED_CAST")
            return NoteViewModel(repository) as T
        }
        // If it's a different ViewModel, throw an exception
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
