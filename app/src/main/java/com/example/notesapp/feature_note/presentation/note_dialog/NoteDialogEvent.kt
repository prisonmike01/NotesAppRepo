package com.example.notesapp.feature_note.presentation.note_dialog

sealed interface NoteDialogEvent {

    // ha hosszan nyomunk a notera, részletek diaglok
    object ShowDialog: NoteDialogEvent
    object HideDialog: NoteDialogEvent
}