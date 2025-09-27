package com.example.notesapp.feature_note.data.data_source

import androidx.room.*
import com.example.notesapp.feature_note.domain.model.Note
import kotlinx.coroutines.flow.Flow

/**
 * Benne az összes eljárás, amivel a Note táblát módosítjuk.
 *
 * Nem kell megírni a kifejtésüket mert az annotation processor megírja.
 * A metódusok suspendek (kiv. getNotes(): Flow<>), mert coroutinban futnak.
 */
@Dao
interface NoteDao {

    /**
     * Flow, mert értesít ha változás van a táblában.
     * List<Note-t> emitel
     */
    @Query("SELECT * FROM note")
    fun getNotes(): Flow<List<Note>>

    // több getet is lehetne belerakni pl. más fajta orderekkel

    @Query("SELECT * FROM note WHERE id = :id")
    suspend fun getNoteById(id: Int): Note?

    // @Upsert, update+insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)
}
