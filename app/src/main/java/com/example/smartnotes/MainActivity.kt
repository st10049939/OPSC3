package com.example.smartnotes

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartnotes.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var notesAdapter: NotesAdapter

    // Use the correct ViewModel initialization
    private val noteViewModel: NoteViewModel by viewModels {
        NoteViewModelFactory((application as NoteApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        // 1. Set the toolbar
        setSupportActionBar(binding.toolbar)

        // 2. Check if the user is logged in. If not, send them back to LoginActivity.
        if (auth.currentUser == null) {
            goToLoginActivity()
            return // Stop executing code in this activity
        }

        // 3. Set up the RecyclerView
        setupRecyclerView()

        // 4. Tell the ViewModel to load notes for the CURRENTLY logged-in user.
        noteViewModel.loadNotesForCurrentUser()

        // 5. Observe the notes and update the UI
        noteViewModel.userNotes.observe(this) { notes ->
            // Handle the empty state UI
            if (notes.isNullOrEmpty()) {
                binding.notesRecyclerView.visibility = View.GONE
                binding.emptyView.visibility = View.VISIBLE
            } else {
                binding.notesRecyclerView.visibility = View.VISIBLE
                binding.emptyView.visibility = View.GONE
                notesAdapter.updateNotes(notes)
            }
        }

        // 6. *** THIS IS THE FIX ***
        // The code to start NoteEditorActivity was missing.
        binding.fabAddNote.setOnClickListener {
            val intent = Intent(this, NoteEditorActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupRecyclerView() {
        notesAdapter = NotesAdapter(mutableListOf()) { note ->
            // This makes each note clickable to edit
            val intent = Intent(this, NoteEditorActivity::class.java).apply {
                putExtra("EXTRA_NOTE", note)
            }
            startActivity(intent)
        }

        binding.notesRecyclerView.apply {
            adapter = notesAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    // --- Menu Handling (Logout and Settings) ---

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.action_logout -> {
                auth.signOut()
                Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show()
                goToLoginActivity()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun goToLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}
