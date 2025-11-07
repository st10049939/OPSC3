package com.example.smartnotes

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope

/**
 * This is the Room database for the app.
 * It lists the entities (tables) and provides the DAOs.
 */
@Database(entities = [Note::class], version = 2, exportSchema = false) // Increased version for migration
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {
        // Volatile ensures that the INSTANCE is always up-to-date and the same for all execution threads.
        @Volatile
        private var INSTANCE: NoteDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): NoteDatabase {
            // Return the existing instance if it's not null, otherwise create a new one.
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "note_database"
                )
                    // Wipes and rebuilds instead of migrating if no Migration object is provided.
                    // Since we added the 'userId' and 'timestamp' columns, this is necessary.
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}

        