package com.example.notesapp.feature_note.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.notesapp.feature_note.domain.model.Note

@Database(
    entities = [Note::class], // különbözű táblák
    version = 1 // ha van újabb verzió, akkor hogy kell migratelni az régi adatot
)
abstract class NoteDatabase: RoomDatabase() {

    abstract val noteDao: NoteDao // ezt is a room implementálja

    // itt lehetnek felsorolva a daok
    companion object {
        const val DATABASE_NAME = "notes_db"
    }
}