package com.example.smartnotes

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

/**
 * The Application class is a base class that contains global application state.
 * We use it here to create a single, shared instance of our database and repository.
 */
class NoteApplication : Application() {

    // No need to cancel this scope as it'll live for the duration of the app
    private val applicationScope = CoroutineScope(SupervisorJob())

    // Using "lazy" so the database and repository are only created when they're first needed
    val database by lazy { NoteDatabase.getDatabase(this, applicationScope) }

    // This is the 'repository' property that your MainActivity is trying to access.
    val repository by lazy { NoteRepository(database.noteDao()) }
}
