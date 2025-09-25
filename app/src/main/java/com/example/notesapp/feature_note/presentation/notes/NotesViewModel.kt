package com.example.notesapp.feature_note.presentation.notes

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.feature_note.domain.model.Note
import com.example.notesapp.feature_note.domain.use_case.NoteUseCases
import com.example.notesapp.feature_note.domain.util.NoteOrder
import com.example.notesapp.feature_note.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

/**
 * Használja a UseCaseket, az eredményeit egy State-be teszi, ami
 * reprezentálja mi a fontos a UI részére. A UI figyeli a Statet.
 */
@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases // ez note feature a usecase wrappere
) : ViewModel() {

    /*
    explicit backing field-el:

    val state: State<NotesState>
        field: mutableStateOf<NotesState> =
        mutableStateOf(NotesState())

    */

    // ez tartalmazza azokat az értékeket, amiket a UI figyel
    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state

    private var getNotesJob: Job? = null

    private var recentlyDeletedNote: Note? = null

    init {
        // ahogy elindul az app hívjuk is
        getNotes(NoteOrder.Date(OrderType.Descending))
    }

    /**
     * Ezt triggeleljük a UI-ból, átadjuk a hozzátartozó NotesEventet.
     * Itt az a dolga a ViewModell-nek hogy hívja a megfelelő UseCase-t.
     */
    fun onEvent(event: NotesEvent) {
        when(event) {
            is NotesEvent.Order -> {
                // ha egyáltalán változott-e a sorrend (ugyan azt a gombot nyomtuk?)
                // ::class nélkül referenciát tesztelnénk, ami mindig hamis lenne :(
                if(state.value.noteOrder::class == event.noteOrder::class &&
                        state.value.noteOrder.orderType == event.noteOrder.orderType
                ) {
                    return
                }

                // átadjuk, milyen sorrenddel kérjük
                getNotes(event.noteOrder)
            }
            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    // itt hívjuk az 'suspend operator fun invoke'-t
                    noteUseCases.deleteNote(event.note)
                    recentlyDeletedNote = event.note
                }
            }
            is NotesEvent.RestoreNote -> {
                viewModelScope.launch {
                    noteUseCases.addNote(recentlyDeletedNote ?: return@launch)

                    // ha valahogy véletlen többször hívnánk a UI-on, nem adnánk újra hozzá többször (?: megfogja)
                    recentlyDeletedNote = null
                }
            }
            // csak itt nem használunk a UseCase-t
            is NotesEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun getNotes(noteOrder: NoteOrder) {

        // mivel minden egyes hívásnál egy egy új példányt kapunk flow-ból,
        // a régi coroutine-t leállítjuk, ami már figyeli a db-t
        getNotesJob?.cancel()

        getNotesJob = noteUseCases.getNotes(noteOrder)
            // on each emission
            .onEach { notes ->
                _state.value = state.value.copy(
                    notes = notes,
                    noteOrder = noteOrder // az átadott noteOrder lesz az új order
                )
            }
            .launchIn(viewModelScope)
    }
}