import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.finalproject323.R
import com.example.finalproject323.Restaurant

class RecentRestaurantsAdapter(
    private val context: Context,
    private var restaurants: List<Restaurant>,
    private val onItemClick: (Restaurant) -> Unit
) : RecyclerView.Adapter<RecentRestaurantsAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.restaurantImageView)
        val nameTextView: TextView = view.findViewById(R.id.restaurantNameTextView)

        init {
            view.setOnClickListener {
                onItemClick(restaurants[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recent_restaurant, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val restaurant = restaurants[position]
        holder.nameTextView.text = restaurant.name
        if (restaurant.imageUrls.isNotEmpty()) {
            Glide.with(context)
                .load(restaurant.imageUrls.first()) // Load the first image URL
                .placeholder(R.drawable.chipotle) // Replace with your placeholder image
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
