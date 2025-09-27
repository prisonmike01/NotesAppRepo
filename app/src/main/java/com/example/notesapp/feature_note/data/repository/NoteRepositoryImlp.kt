package com.example.notesapp.feature_note.data.repository

import com.example.notesapp.feature_note.data.data_source.persistence.NoteDao
import com.example.notesapp.feature_note.domain.model.Note
import com.example.notesapp.feature_note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

/**
 *  Amit a domain layerben definiáltunk, annak az implementációja.
 *  NoteDao-t dependenciként használja.
 *
 *  lsd. NoteRepository
 */
class NoteRepositoryImlp(
    private val dao: NoteDao // ide kéne a remote API dao
) : NoteRepository {

    override fun getNotes(): Flow<List<Note>> {
        return dao.getNotes()                // API-val ide kell data logic
    }

    override suspend fun getNoteById(id: Int): Note? {
        return dao.getNoteById(id)
    }

    override suspend fun insertNote(note: Note) {
        dao.insertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        dao.deleteNote(note)
    }
}
