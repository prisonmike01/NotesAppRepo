package com.example.notesapp.feature_note.presentation.note_dialog

import com.example.notesapp.feature_note.domain.model.Note


// combinees screen states
data class NoteDialogState(
    val note: Note? = null,
    val isDetailedNoteVisible: Boolean = false
)
