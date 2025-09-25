package com.example.notesapp.feature_note.domain.util

/**
 * Definiálja, hogy mi alapján (Title, Date vagy Color)
 * milyen irányba (OrderType) rendezünk.
 * */
sealed class NoteOrder(val orderType: OrderType) {
    class Title(orderType: OrderType) : NoteOrder(orderType)
    class Date(orderType: OrderType) : NoteOrder(orderType)
    class Color(orderType: OrderType) : NoteOrder(orderType)


    // ha dataclassok lennének fentebb, akkor lenne alapból copy
    fun copy(ordertype: OrderType) : NoteOrder {
        // amikor a jelenlegi noteorder..
        return when(this) {
            is Title -> Title(ordertype)
            is Date -> Date(ordertype)
            is Color -> Color(ordertype)
        }
    }
}
