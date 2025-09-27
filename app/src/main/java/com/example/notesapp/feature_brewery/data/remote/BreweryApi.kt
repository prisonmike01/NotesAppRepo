package com.example.notesapp.feature_brewery.data.remote

import retrofit2.http.GET

interface BreweryApi {

    @GET("/v1/breweries")
    suspend fun getBreweries(): List<Brewery>

}