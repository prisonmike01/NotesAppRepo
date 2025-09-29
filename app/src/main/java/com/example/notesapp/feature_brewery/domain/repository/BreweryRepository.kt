package com.example.notesapp.feature_brewery.domain.repository

import com.example.notesapp.feature_brewery.data.remote.dto.BreweryDetailDto
import com.example.notesapp.feature_brewery.data.remote.dto.BreweryDto

// one function for each API request we have
interface BreweryRepository {

    suspend fun getBreweries(): List<BreweryDto>

    suspend fun getBreweryById(id: String): BreweryDetailDto
}
