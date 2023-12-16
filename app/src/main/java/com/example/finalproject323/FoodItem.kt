package com.example.finalproject323

/**
 * represents an item of food with an id, name, price, and quantity
 * @property id unique identifier for the food item
 * @property name name of the food item
 * @property price price of the food item
 * @property quantity number of times this item has been ordered or added to the cart
 */
data class FoodItem(
    val id: Int,
    val name: String,
    val price: Double,
    var quantity: Int = 0
)
