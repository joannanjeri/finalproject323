package com.example.finalproject323

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * adapter for displaying a list of food items
 * @property items list of food items to be displayed
 * @property onItemChanged callback function when an item is added or deleted
 */
class FoodAdapter(
    private val items: List<FoodItem>,
    private val onItemChanged: (FoodItem) -> Unit
) : RecyclerView.Adapter<FoodAdapter.ViewHolder>() {

    /**
     * creates new views for food items
     * @param parent the ViewGroup into which the new view will be added after it is bound to an adapter position
     * @param viewType the view type of the new view
     * @return a new ViewHolder that holds a View for the food items
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_food, parent, false)
        return ViewHolder(view)
    }

    /**
     * binds the data to the view holder at the specified position
     * @param holder the view holder to bind to
     * @param position the position in the data set
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    /**
     * returns the total number of items in the data set
     * @return the item count
     */
    override fun getItemCount() = items.size

    /**
     * a ViewHolder describes an item view and its place within the RecyclerView
     * @property view the root view of the item
     */
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val nameText: TextView = view.findViewById(R.id.foodNameText)
        private val priceText: TextView = view.findViewById(R.id.foodPriceText)
        private val addButton: Button = view.findViewById(R.id.addButton)
        private val deleteButton: Button = view.findViewById(R.id.deleteButton)
        private val quantityText: TextView = view.findViewById(R.id.quantityText)

        /**
         * binds the food item data to the views
         * @param foodItem the food item to bind to the views
         */
        fun bind(foodItem: FoodItem) {
            nameText.text = foodItem.name
            priceText.text = "$${foodItem.price}"
            quantityText.text = foodItem.quantity.toString()

            addButton.setOnClickListener {
                foodItem.quantity++
                quantityText.text = foodItem.quantity.toString()
                onItemChanged(foodItem)
            }

            deleteButton.setOnClickListener {
                if (foodItem.quantity > 0) {
                    foodItem.quantity--
                    quantityText.text = foodItem.quantity.toString()
                    onItemChanged(foodItem)
                }
            }
        }
    }
}
