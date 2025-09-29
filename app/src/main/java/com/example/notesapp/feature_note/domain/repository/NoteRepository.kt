package com.example.notesapp.feature_note.domain.repository

import com.example.notesapp.feature_note.domain.model.Note
import kotlinx.coroutines.flow.Flow

/**
 *  Az IMLP -> Directly eléri a data source-okat: pl. database-t v/és remote API-t.
 *
 *  Definiálja, milyen data manipulációk léteznek, de nem részletezi azokat.
 *  Interface, hogy lehessen tesztelni hamis repositorykkal.
 *  A repository feladata, hogy vegye a data sourceok és döntse el, hogy
 *  melyiket kell továbbítani a hozzátartozó UseCase-nek.
 *  Ha van cache és API akkor decison logic és API request error check is.
 *  Ezáltal a domain layer független lesz a data layertől.
 */
interface NoteRepository {

    fun getNotes(): Flow<List<Note>>

    suspend fun getNoteById(id: Int): Note?

    suspend fun insertNote(note: Note)

    suspend fun deleteNote(note: Note)
}
