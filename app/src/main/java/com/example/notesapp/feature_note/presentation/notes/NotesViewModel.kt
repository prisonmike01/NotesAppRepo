package com.example.notesapp.feature_note.presentation.notes

import androidx.lifecycle.ViewModel
import com.example.notesapp.feature_note.domain.use_case.NotesUseCases
import jakarta.inject.Inject

class NotesViewM @Inject constructor(
    private val noteUseCases: NotesUseCases
) : ViewModel() {


}