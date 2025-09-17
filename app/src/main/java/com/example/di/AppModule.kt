package com.example.di

import android.app.Application
import androidx.room.Room
import com.example.notesapp.feature_note.data.data_source.NoteDatabase
import com.example.notesapp.feature_note.domain.repository.NoteRepository
import com.example.notesapp.feature_note.data.repository.NoteRepositoryImlp
import com.example.notesapp.feature_note.domain.use_case.DeleteNotesUseCase
import com.example.notesapp.feature_note.domain.use_case.GetNotesUseCase
import com.example.notesapp.feature_note.domain.use_case.NotesUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

// egy modul -> egy feature
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImlp(db.noteDao) // ezekkel tesztelni a db-t
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NotesUseCases {
        return NotesUseCases(
            getNotes = GetNotesUseCase(repository),
            deleteNote = DeleteNotesUseCase(repository)
        )
    }
}
