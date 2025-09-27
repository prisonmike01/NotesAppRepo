package com.example.notesapp.feature_note.data.data_source.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.notesapp.feature_note.domain.model.Note

/**
 * A Room adatbázis osztálya.
 *
 * entities: különbözű táblák
 *
 * version: ha van újabb verzió, akkor hogy kell migratelni az régi adatot
 */
@Database(
    entities = [Note::class],
    version = 1
)
abstract class NoteDatabase: RoomDatabase() {

    abstract val noteDao: NoteDao // ezt is a room implementálja

    // itt lehetnek felsorolva a daok
    companion object {
        const val DATABASE_NAME = "notes_db"
    }
}