package com.example.finalproject323

import RecentRestaurantsAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

/**
 * fragment for displaying a list of recent restaurants
 */
class RecentRestaurantsFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecentRestaurantsAdapter

    /**
     * creates the view for the fragment
     * inflates the layout for this fragment
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_home, container, false)
    }

    /**
     * called after the view is created
     * sets up the recyclerview and adapter for displaying restaurants
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerViewFavoriteRestaurants)
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        adapter = RecentRestaurantsAdapter(requireContext(), listOf()) {
        }

        recyclerView.adapter = adapter
        fetchRecentRestaurants()
    }

    /**
     * handles the event when a restaurant is selected
     * @param restaurantId the id of the selected restaurant
     */
    private fun onRestaurantSelected(restaurantId: String) {
        (parentFragment as? HomeFragment)?.notifyRestaurantSelected(restaurantId)
    }

    /**
     * fetches recent restaurants from firestore and updates the adapter
     */
    private fun fetchRecentRestaurants() {
        FirebaseFirestore.getInstance().collection("restaurants")
            .get()
            .addOnSuccessListener { documents ->
                val restaurantsList = documents.mapNotNull { it.toObject(Restaurant::class.java) }
                adapter.updateRestaurants(restaurantsList)
            }
            .addOnFailureListener { exception ->
                Log.e("RecentRestaurantsFragment", "Error fetching restaurants", exception)
            }
    }
}