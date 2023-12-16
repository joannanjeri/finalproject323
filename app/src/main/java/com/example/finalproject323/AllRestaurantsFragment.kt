package com.example.finalproject323

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
 * fragment responsible for displaying all restaurants from firestore
 */
class AllRestaurantsFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AllRestaurantsAdapter

    /**
     * creates and returns the view hierarchy associated with the fragment
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_home, container, false)
    }

    /**
     * called immediately after onCreateView has returned, but before any saved state has been restored into the view
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerViewAllRestaurants)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = AllRestaurantsAdapter(requireContext(), listOf()) {
        }

        recyclerView.adapter = adapter

        fetchAllRestaurants()
    }

    /**
     * handles the event when a restaurant is selected
     * @param restaurantId the id of the selected restaurant
     */
    private fun onRestaurantSelected(restaurantId: String) {
        (parentFragment as? HomeFragment)?.notifyRestaurantSelected(restaurantId)
    }


    /**
     * fetches all restaurants from the order collection in firestore
     */
    private fun fetchAllRestaurants() {
        FirebaseFirestore.getInstance().collection("order").get()
            .addOnSuccessListener { documents ->
                val restaurantsList = documents.mapNotNull { document ->
                    document.toObject(Restaurant::class.java)
                }
                adapter.updateRestaurants(restaurantsList)
            }
            .addOnFailureListener { exception ->
                Log.e("AllRestaurantsFragment", "Error fetching restaurants", exception)
            }
    }

}