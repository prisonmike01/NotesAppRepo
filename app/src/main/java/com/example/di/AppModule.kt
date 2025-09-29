package com.example.di

import android.app.Application
import androidx.room.Room
import com.example.notesapp.core.Constants
import com.example.notesapp.feature_brewery.data.remote.BreweryApi
import com.example.notesapp.feature_brewery.data.repository.BreweryRepositoryImlp
import com.example.notesapp.feature_brewery.domain.repository.BreweryRepository
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
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * A feature-hot tartozó dependenciket manageljük.
 *
 * Egy modulhoz egy feature tartozik. Segít, hogy a dependenciek kicserélhetőek legyenek.
 * Kívülről, a modulból injecteljük a dependency-ket.
 */
@Module // Daggertől
@InstallIn(SingletonComponent::class) // Azért singletonba, mert egész app alatt ÉLnek az apik amik ebben a modulban vannak kezelve.
object AppModule {

    @Provides // mert a function provideol dependenciet
    @Singleton // nem ue mint a SigletonComponent -> ez scope
    // ha nem lenne itt, akk minden új alkalommal új PÉLDÁNY lenne készítve, amikor visszatér
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

    ///

    @Provides
    @Singleton
    fun provideBreweryApi(): BreweryApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BreweryApi::class.java)
    }

    @Provides
    @Singleton
    fun provideBreweryRepository(api: BreweryApi): BreweryRepository {
        return BreweryRepositoryImlp(api)
    }

}
