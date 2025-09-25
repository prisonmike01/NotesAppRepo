package com.example.notesapp.feature_note.presentation.notes

import com.example.notesapp.feature_note.domain.model.Note
import com.example.notesapp.feature_note.domain.util.NoteOrder
import com.example.notesapp.feature_note.domain.util.OrderType

/**
 *  Egy olyan State wrapper, ami reprezentálja a pillanatnyi UI állapotot
 *  a Notes képernyőnek.
 *
 *  design: ?Milyen változók relevánsak a UI megjelenítés számára?
 *
 *  A ViewModellben menjtünk el, configChanged is túl fog élni (pl képernyő forgatás).
 */
data class NotesState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
