package com.example.notesapp.feature_note.domain.use_case

/**
 * Note UseCases wrapper, ezt kell a ViewModellbe injectálni.
 */
//
data class NoteUseCases(
    val getNotes: GetNotesUseCase,
    val deleteNote: DeleteNoteUseCase,
    val addNote: AddNoteUseCase,
    val getNote: GetNoteUseCase
)
