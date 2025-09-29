package com.example.notesapp.feature_note.presentation.note_dialog.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.notesapp.feature_note.presentation.note_dialog.NoteDialogEvent
import com.example.notesapp.feature_note.presentation.note_dialog.NoteDialogState

@Composable
fun NoteDialog(
    state: NoteDialogState,
    onEvent: (NoteDialogEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        modifier = modifier,

        // amikor kivül kattintunk a dialogon
        onDismissRequest = { onEvent(NoteDialogEvent.HideDialog) },

        title = { Text(text = "Details of ${state.note?.title}") },
        confirmButton = {
            TextButton(
                onClick = {
                    onEvent(NoteDialogEvent.HideDialog)
                },
                modifier = modifier.fillMaxWidth()
            ) {
                Text("Confirm")
            }
        }

    )
}