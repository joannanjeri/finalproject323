package com.example.finalproject323

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class AllRestaurantsAdapter(
    private val context: Context,
    private var restaurants: List<Restaurant>,
    private val onItemClick: (Restaurant) -> Unit
) : RecyclerView.Adapter<AllRestaurantsAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.allRestaurantImageView)
        val nameTextView: TextView = view.findViewById(R.id.allRestaurantNameTextView)
        val locationTextView: TextView = view.findViewById(R.id.allRestaurantLocationTextView)

        init {
            view.setOnClickListener {
                onItemClick(restaurants[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.all_restaurant, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val restaurant = restaurants[position]
        holder.nameTextView.text = restaurant.name
        holder.locationTextView.text = restaurant.location
        if (restaurant.imageUrls.isNotEmpty()) {
            Glide.with(context)
                .load(restaurant.imageUrls.first()) // Assuming the first image is the main image
                .placeholder(R.drawable.chipotle) // Replace with your placeholder
                .into(holder.imageView)
        } else {
            holder.imageView.setImageResource(R.drawable.chipotle) // Default image if no URLs are provided
        }
    }

    override fun getItemCount(): Int = restaurants.size

    fun updateRestaurants(newRestaurants: List<Restaurant>) {
        this.restaurants = newRestaurants
        notifyDataSetChanged()
    }
}
