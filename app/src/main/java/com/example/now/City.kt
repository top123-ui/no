package com.example.now

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class City(
    val id: Int,
    val name: String,
    val imageResId: Int,
    val latitude: Double,
    val longitude: Double,
    val routes: List<Route> = emptyList()
) : Parcelable

@Parcelize
data class Route(
    val name: String,
    val description: String,
    val points: List<Point>,
    val places: List<Place>
) : Parcelable

@Parcelize
data class Point(
    val lat: Double,
    val lon: Double
) : Parcelable

@Parcelize
data class Place(
    val name: String,
    val type: PlaceType,
    val lat: Double,
    val lon: Double
) : Parcelable

enum class PlaceType {
    CAFE, PARK, MUSEUM
}
