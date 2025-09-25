package com.example.notesapp.feature_note.presentation.notes

import com.example.notesapp.feature_note.domain.model.Note
import com.example.notesapp.feature_note.domain.util.NoteOrder

/**
 *  Egy User Actions wrapper, így
 *  könnyen elküldhetjük az eseményeket a Composable k -ből a ViewModel részére.
 *
 *  ViewModellbe egy when-nel megnézni milyen event jött.
 *
 *  design: ?mi az össszes lehetséges UI action, amik a usertől jöhetnek ezen a képernyőn?
 */
sealed class NotesEvent {
    data class Order(val noteOrder: NoteOrder): NotesEvent()
    data class DeleteNote(val note: Note): NotesEvent()
    object RestoreNote: NotesEvent()
    object ToggleOrderSection: NotesEvent()
}
