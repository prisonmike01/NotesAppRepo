package com.example.notesapp.feature_note.domain.util

/**
 * Növeknő vagy csökkenő.
 */
sealed class OrderType {
    object Ascending : OrderType()
    object Descending : OrderType()

}