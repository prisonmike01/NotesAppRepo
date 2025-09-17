package com.example.notesapp.feature_note.domain.use_case

// ezt kell a view modellbe injectálni
data class NotesUseCases(
    val getNotes: GetNotesUseCase,
    val deleteNote: DeleteNotesUseCase,

)
