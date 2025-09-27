package com.example.di

import android.app.Application
import androidx.room.Room
import com.example.notesapp.feature_note.data.data_source.NoteDatabase
import com.example.notesapp.feature_note.domain.repository.NoteRepository
import com.example.notesapp.feature_note.data.repository.NoteRepositoryImlp
import com.example.notesapp.feature_note.domain.use_case.AddNoteUseCase
import com.example.notesapp.feature_note.domain.use_case.DeleteNoteUseCase
import com.example.notesapp.feature_note.domain.use_case.GetNoteUseCase
import com.example.notesapp.feature_note.domain.use_case.GetNotesUseCase
import com.example.notesapp.feature_note.domain.use_case.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

/**
 * A feature-hot tartozó dependenciket manageljük.
 *
 * Egy modulhoz egy feature tartozik.
 */
@Module // Daggertől

@InstallIn(SingletonComponent::class) // Azért singletonba, mert egész app alatt élnek az apik amik ebben a modulban vannak kezelve.
object AppModule {

    @Provides
    @Singleton // nem ue mint a SigletonComponent -> ez scope
    // ha nem lenne itt, akk minden új alkalommal új példány lenne készítve
    //
    // így már tudja a dagger hogyan készítsen NoteDatabase osztályt
    // tehát bármikor ilyen példányt kérünk, mint pl a ~~NoteRepositoryIMLP-ben
    // akkor a dagger a moduljaban megkeresi és átadja a konstruktorban a háttérben
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,        // az app contex
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NoteRepository {
        // egy másik test AppModulban ezekkel tesztelni a db-t
        return NoteRepositoryImlp(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getNotes = GetNotesUseCase(repository),
            deleteNote = DeleteNoteUseCase(repository),
            addNote = AddNoteUseCase(repository),
            getNote = GetNoteUseCase(repository)
        )
    }
}
