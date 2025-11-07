package com.example.smartnotes

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.smartnotes.databinding.ActivityNoteEditorBinding
import com.google.firebase.auth.FirebaseAuth

class NoteEditorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteEditorBinding

    // This factory is necessary because your ViewModel has a custom constructor
    private val noteViewModel: NoteViewModel by viewModels {
        NoteViewModelFactory((application as NoteApplication).repository)
    }

    private var currentNote: Note? = null
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteEditorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance() // Initialize Firebase Auth

        // Check if we are editing an existing note
        currentNote = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("EXTRA_NOTE", Note::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getSerializableExtra("EXTRA_NOTE") as? Note
        }

        // If we are editing, populate the fields
        currentNote?.let {
            binding.editTextNoteTitle.setText(it.title)
            binding.editTextNoteContent.setText(it.content)
        }

        binding.buttonSave.setOnClickListener {
            saveNote()
        }
    }

    private fun saveNote() {
        val title = binding.editTextNoteTitle.text.toString().trim()
        val content = binding.editTextNoteContent.text.toString().trim()
        val userId = auth.currentUser?.uid

        // --- VALIDATION ---
        if (userId == null) {
            Toast.makeText(this, "Error: User not logged in.", Toast.LENGTH_SHORT).show()
            return // Can't save a note without a user
        }

        if (title.isBlank() && content.isBlank()) {
            Toast.makeText(this, "Cannot save an empty note.", Toast.LENGTH_SHORT).show()
            return
        }

        // --- THIS IS THE FIX ---
        if (currentNote != null) {
            // We are UPDATING an existing note
            val updatedNote = currentNote!!.copy(
                title = title,
                content = content,
                timestamp = System.currentTimeMillis() // Update the timestamp
            )
            noteViewModel.update(updatedNote)
        } else {
            // We are CREATING a new note
            val newNote = Note(
                title = title,
                content = content,
                timestamp = System.currentTimeMillis(), // Set the current time
                userId = userId                        // Set the current user's ID
            )
            noteViewModel.insert(newNote)
        }

        finish() // Go back to the MainActivity
    }
}
