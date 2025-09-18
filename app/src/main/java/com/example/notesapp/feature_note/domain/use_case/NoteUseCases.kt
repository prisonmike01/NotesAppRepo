package com.example.notesapp.feature_note.domain.use_case

// ezt kell a view modellbe injectálni
data class NoteUseCases(
    val getNotes: GetNotesUseCase,
    val deleteNote: DeleteNoteUseCase,
    val addNote: AddNoteUseCase,
    val getNote: GetNoteUseCase
)
