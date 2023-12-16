package com.example.finalproject323

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AllRestaurantsFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AllRestaurantsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_all_restaurants, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerViewAllRestaurants)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Initialize adapter (assuming you have an empty list initially)
        adapter = AllRestaurantsAdapter(requireContext(), listOf()) {
            // Handle item click, navigate to the Restaurant details screen
        }

        recyclerView.adapter = adapter

        fetchAllRestaurants()
    }

    private fun onRestaurantSelected(restaurantId: String) {
        (parentFragment as? HomeFragment)?.notifyRestaurantSelected(restaurantId)
    }

    private fun fetchAllRestaurants() {
        // TODO: Fetch all restaurants from Firestore and pass them to the adapter
    }

}