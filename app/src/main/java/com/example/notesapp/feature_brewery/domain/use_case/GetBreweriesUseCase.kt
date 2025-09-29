package com.example.notesapp.feature_brewery.domain.use_case

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.example.notesapp.core.Resource
import com.example.notesapp.feature_brewery.data.remote.dto.toBrewery
import com.example.notesapp.feature_brewery.domain.model.Brewery
import com.example.notesapp.feature_brewery.domain.repository.BreweryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetBreweriesUseCase @Inject constructor(
    val repository: BreweryRepository
) {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    operator fun invoke(): Flow<Resource<List<Brewery>>> = flow {
        try {
            emit(Resource.Loading())
            val breweries = repository.getBreweries().map { it.toBrewery() }
            emit(Resource.Success(breweries))

        } catch (e: HttpException) {
            // ha nem volt sikeres a kérés
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))

        } catch (e: IOException) {
            // ha a repository vagy api nem tud beszélni a remote apival, pl. no internet
            emit(Resource.Error("Couldn't reach server."))
        }
    }
}