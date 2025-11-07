package com.example.smartnotes


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// The class definition itself is mostly correct.
class NotesAdapter(
    private var notes: List<Note>,
    private val onNoteClicked: (Note) -> Unit
) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    // The ViewHolder class needs to be defined INSIDE the adapter class,
    // or as a separate top-level class. Inside is common and fine.
    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.textViewNoteTitle)
        val content: TextView = itemView.findViewById(R.id.textViewNoteContent)
    }

    // This function inflates the XML layout for each item.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(view)
    }

    // This function binds the data from a 'Note' object to the views in the ViewHolder.
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = notes[position]

        holder.title.text = currentNote.title
        holder.content.text = currentNote.content

        holder.itemView.setOnClickListener {
            onNoteClicked(currentNote)
        }
    }

    // Returns the total number of items in the list.
    override fun getItemCount() = notes.size

    // A helper function to update the adapter's data and refresh the list.
    // Using notifyDataSetChanged() is simple but less efficient. For this project, it's perfectly fine.
    fun updateNotes(newNotes: List<Note>) {
        notes = newNotes
        notifyDataSetChanged()
    }
}
