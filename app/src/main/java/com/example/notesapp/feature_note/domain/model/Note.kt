package com.example.notesapp.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.notesapp.ui.theme.*

/**
 *  Core business object, nem tud semmit arról hogy hogyan tárolják vagy jelenítik meg.
 *
 *  Benne egy companion object: noteColors.
 */
@Entity // table in room, def table name a class name
data class Note(
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    @PrimaryKey val id: Int? = null // auto generált; def param miatt utoljára kell
) {

    // companion: related to the class, but not the the instance ~ singleton
    // a példányok között ua a noteColors
    // példány nélkül is használható
    companion object {
        val noteColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}

class InvalidNoteException(message: String): Exception(message)
