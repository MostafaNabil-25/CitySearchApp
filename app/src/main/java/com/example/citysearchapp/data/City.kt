package com.example.citysearchapp.data

data class City(
    val country: String,
    val name: String,
    val _id: Int,
    val coord: Coord
)

data class Coord(
    val lon: Double,
    val lat: Double
)