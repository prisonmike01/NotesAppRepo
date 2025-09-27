package com.example.notesapp.feature_brewery.data.remote.dto

data class BreweryDto(
    val id: String,
    val name: String,
    val city: String,
    val state_province: String,

    val address_2: Any,
    val address_3: Any,
    val address_1: String,
    val brewery_type: String,
    val country: String,
    val latitude: Double,
    val longitude: Double,
    val phone: String,
    val postal_code: String,
    val state: String,
    val street: String,
    val website_url: String
)