package com.example.notesapp.feature_note.data.data_source

import androidx.room.*
import com.example.notesapp.feature_note.domain.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    // az összes eljárás, amivel a Note táblát módosítjuk

    @Query("SELECT * FROM note")
    fun getNotes(): Flow<List<Note>>
    // flow, mert értesít ha változás van a táblában,
    // List<Note-t> emitel

    // több getet is lehetne belerakni pl. más fajta orderekkel

    @Query("SELECT * FROM note WHERE id = :id")
    suspend fun getNoteById(id: Int): Note?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)
    // nem kell megírni a kifejtését mert az annotation processor megírja
    // @Upsert, update+insert
    // suspendek, mert coroutinban futnak

    @Delete
    suspend fun deleteNote(note: Note)
}
