package com.example.finalproject323

import com.google.firebase.firestore.GeoPoint

/**
 * data class representing a restaurant
 * @property id unique identifier for the restaurant
 * @property name name of the restaurant
 * @property location geographical location of the restaurant
 * @property imageUrls list of URLs for images of the restaurant
 */
data class Restaurant(
    val id: String = "",
    val name: String = "",
    val location: GeoPoint = GeoPoint(0.0, 0.0),
    val imageUrls: List<String> = emptyList()
) {
    /**
     * secondary constructor to create an empty restaurant object
     */
    constructor() : this("", "", GeoPoint(0.0, 0.0), listOf())
}
