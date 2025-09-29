package com.example.notesapp.feature_brewery.data.remote.dto

import com.example.notesapp.feature_brewery.domain.model.Brewery
import com.example.notesapp.feature_brewery.domain.model.BreweryDetail

// ha a get brewerybyid más jsno-t adna vissza akkor ahhoz is kéne külön
// deatiled-brewerydto-t csinálni
// de így csak egy másik model a domain layerben
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


fun BreweryDto.toBrewery(): Brewery {
    return Brewery(
        id = id,
        name = name,
        city = city,
        state_province = state_province
    )
}
