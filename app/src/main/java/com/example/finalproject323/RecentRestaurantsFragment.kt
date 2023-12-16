package com.example.finalproject323

import RecentRestaurantsAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecentRestaurantsFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecentRestaurantsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recent_restaurants, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerViewFavoriteRestaurants)
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        // Initialize adapter (assuming you have an empty list initially)
        adapter = RecentRestaurantsAdapter(requireContext(), listOf()) {
            // Handle item click, navigate to the Restaurant details screen
        }

        recyclerView.adapter = adapter
        fetchRecentRestaurants()
    }

    private fun onRestaurantSelected(restaurantId: String) {
        (parentFragment as? HomeFragment)?.notifyRestaurantSelected(restaurantId)
    }

    private fun fetchRecentRestaurants() {
        // TODO: Fetch recent restaurants from Firestore and pass them to the adapter
    }
}