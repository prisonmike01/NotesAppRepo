package com.example.notesapp.feature_note.domain.util

/**
 * Definiálja, hogy mi alapján (Title, Date vagy Color)
 * milyen irányba (OrderType) rendezünk.
 * */
sealed class NoteOrder(val orderType: OrderType) {
    class Title(orderType: OrderType) : NoteOrder(orderType)
    class Date(orderType: OrderType) : NoteOrder(orderType)
    class Color(orderType: OrderType) : NoteOrder(orderType)

    // OrderSection *** miatt kell ez
    // pass the new ordertype, keep the noteOrder, but we change the orderType
    // ha dataclassok lennének fentebb, akkor lenne alapból copy
    fun copy(orderType: OrderType) : NoteOrder {
        // amikor a jelenlegi noteorder..
        return when(this) {
            is Title -> Title(orderType)
            is Date -> Date(orderType)
            is Color -> Color(orderType)
        }
    }
}
