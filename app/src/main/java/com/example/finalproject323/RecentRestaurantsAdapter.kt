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

/**
 * adapter for displaying a list of recent restaurants in a RecyclerView
 */
class RecentRestaurantsAdapter(
    private val context: Context,
    private var restaurants: List<Restaurant>,
    private val onItemClick: (Restaurant) -> Unit
) : RecyclerView.Adapter<RecentRestaurantsAdapter.ViewHolder>() {

    /**
     * provides a reference for the views needed in each item of the list
     */
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.restaurantImageView)
        val nameTextView: TextView = view.findViewById(R.id.restaurantNameTextView)
        val locationTextView: TextView = view.findViewById(R.id.restaurantLocationTextView)

        init {
            nameTextView.setOnClickListener {
                val restaurant = restaurants[adapterPosition]
                onItemClick(restaurant)
            }
        }

    }

    /**
     * inflates the item layout and creates the ViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recent_restaurant, parent, false)
        return ViewHolder(view)
    }

    /**
     * binds the restaurant data to each item of the RecyclerView
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
     * returns the total number of items in the data set
     */
    override fun getItemCount(): Int = restaurants.size

    /**
     * updates the list of restaurants and notifies the adapter to refresh the list
     */
    fun updateRestaurants(newRestaurants: List<Restaurant>) {
        this.restaurants = newRestaurants
        notifyDataSetChanged()
    }
}
