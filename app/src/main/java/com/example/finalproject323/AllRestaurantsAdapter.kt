package com.example.finalproject323

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

/**
 * adapter for displaying all restaurants in a recyclerview
 * @property context context in which the adapter is running
 * @property restaurants list of restaurant objects to be displayed
 * @property onItemClick lambda function to handle item clicks
 */
class AllRestaurantsAdapter(
    private val context: Context,
    private var restaurants: List<Restaurant>,
    private val onItemClick: (Restaurant) -> Unit
) : RecyclerView.Adapter<AllRestaurantsAdapter.ViewHolder>() {

    /**
     * viewholder class that holds the views for each item
     */
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.allRestaurantImageView)
        val nameTextView: TextView = view.findViewById(R.id.allRestaurantNameTextView)
        val locationTextView: TextView = view.findViewById(R.id.allRestaurantLocationTextView)

        init {
            nameTextView.setOnClickListener {
                val restaurant = restaurants[adapterPosition]
                onItemClick(restaurant)
            }
        }

    }

    /**
     * called when recyclerview needs a new viewholder of the given type to represent an item
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.all_restaurant, parent, false)
        return ViewHolder(view)
    }

    /**
     * called by recyclerview to display the data at the specified position
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val restaurant = restaurants[position]
        holder.nameTextView.text = restaurant.name

        val locationString = "Lat: ${restaurant.location.latitude}, Long: ${restaurant.location.longitude}"
        holder.locationTextView.text = locationString

        Glide.with(context)
            .load(restaurant.imageUrls.firstOrNull())
            .placeholder(R.drawable.chipotle)
            .error(R.drawable.acount)
            .into(holder.imageView)

        holder.itemView.setOnClickListener { onItemClick(restaurant) }
    }

    /**
     * returns the total number of items in the data set held by the adapter
     */
    override fun getItemCount(): Int = restaurants.size

    /**
     * update the restaurants list with new data and notify any registered observers
     * @param newRestaurants the new list of restaurants to replace the old one
     */
    fun updateRestaurants(newRestaurants: List<Restaurant>) {
        this.restaurants = newRestaurants
        notifyDataSetChanged()
    }
}
