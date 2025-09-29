package com.example.notesapp.feature_note.domain.use_case

import com.example.notesapp.feature_note.domain.model.InvalidNoteException
import com.example.notesapp.feature_note.domain.model.Note
import com.example.notesapp.feature_note.domain.repository.NoteRepository

/**
 *  A UseCase-ek a busniss logic-okat,rule-okat tartalmazzák. Használják a repositorykat.
 *
 *  Single user actions, amint a ViewModellek hívnak.
 *  Egy metódusok lehet, ami public: hívják pl. execute-nak VAGY
 *  operator invoke funcionnal lehet a UseCase hívni mint egy metódust.
 *
 *  Nem szabad hogy tudják, honnan kapta a NoteRepositoryImpl az adatot (pl. Room-ból).
 *  Az összes use case kivéve a 'UseCases', a NoteRepositorytól függ.
 *  Nagyon újrahasznosíthatóvá teszi a kódokat.
 */
class AddNoteUseCase(
    private val repository: NoteRepository // fontos hogy ne a repository implementációja legyen, mert így könnyen kicserélhető testhez
) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if(note.title.isBlank()) {
            throw InvalidNoteException("The title of the note can't be empty.")
        }
        if(note.content.isBlank()) {
            throw InvalidNoteException("The content of the note can't be empty.")
        }

        repository.insertNote(note)
    }
}
