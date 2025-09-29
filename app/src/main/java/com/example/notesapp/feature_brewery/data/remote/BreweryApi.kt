package com.example.notesapp.feature_brewery.data.remote

import com.example.notesapp.feature_brewery.data.remote.dto.BreweryDetailDto
import com.example.notesapp.feature_brewery.data.remote.dto.BreweryDto
import retrofit2.http.GET
import retrofit2.http.Path

interface BreweryApi {

    @GET("/v1/breweries")
    suspend fun getBreweries(): List<BreweryDto>

    @GET("/v1/breweries/{obdb-id}")
    suspend fun getBreweryById(@Path("obdb-id") obdbId: String): BreweryDetailDto

}