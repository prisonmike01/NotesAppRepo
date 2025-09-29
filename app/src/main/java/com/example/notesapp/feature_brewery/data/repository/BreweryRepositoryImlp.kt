package com.example.notesapp.feature_brewery.data.repository
import com.example.notesapp.feature_brewery.data.remote.BreweryApi
import com.example.notesapp.feature_brewery.data.remote.dto.BreweryDetailDto
import com.example.notesapp.feature_brewery.data.remote.dto.BreweryDto
import com.example.notesapp.feature_brewery.domain.repository.BreweryRepository
import javax.inject.Inject

class BreweryRepositoryImlp @Inject constructor(
    private val api: BreweryApi
) : BreweryRepository
{
    override suspend fun getBreweries(): List<BreweryDto> {
        return api.getBreweries()
    }

    override suspend fun getBreweryById(breweryId: String): BreweryDetailDto {
        return api.getBreweryById(breweryId)
    }
}
