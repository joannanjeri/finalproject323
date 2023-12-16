package com.example.finalproject323

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FoodAdapter(
    private val items: List<FoodItem>,
    private val onItemChanged: (FoodItem) -> Unit // Callback for when an item is added or removed
) : RecyclerView.Adapter<FoodAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_food, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount() = items.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val nameText: TextView = view.findViewById(R.id.foodNameText)
        private val priceText: TextView = view.findViewById(R.id.foodPriceText)
        private val addButton: Button = view.findViewById(R.id.addButton)
        private val deleteButton: Button = view.findViewById(R.id.deleteButton)
        private val quantityText: TextView = view.findViewById(R.id.quantityText)

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
