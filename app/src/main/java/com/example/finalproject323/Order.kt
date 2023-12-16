package com.example.finalproject323

/**
 * represents an individual order
 * @property id unique identifier for the order
 * @property name name associated with the order
 * @property date the date when the order was placed or completed
 */
data class Order(
    val id: Int,
    val name: String,
    val date: String
)
