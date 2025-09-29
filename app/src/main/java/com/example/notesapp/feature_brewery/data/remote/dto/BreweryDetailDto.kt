package com.example.notesapp.feature_brewery.data.remote.dto

import com.example.notesapp.feature_brewery.domain.model.BreweryDetail

interface BreweryDetailDto {
    val website_url: String
    val street: String
}


fun BreweryDetailDto.toBreweryDetail(): BreweryDetail {
    return BreweryDetail(
        website_url = website_url,
        street = street
    )
}
