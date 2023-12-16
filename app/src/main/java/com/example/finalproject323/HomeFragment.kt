package com.example.finalproject323

//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.FragmentPagerAdapter
//import androidx.viewpager.widget.ViewPager
//import com.example.finalproject323.AllRestaurantsFragment
//import com.example.finalproject323.R
//import com.example.finalproject323.RecentRestaurantsFragment
//import com.google.android.material.tabs.TabLayout
//
//class HomeFragment : Fragment() {
//
//
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        val view = inflater.inflate(R.layout.fragment_home, container, false)
//
//        val tabLayout: TabLayout = view.findViewById(R.id.tabLayout)
//        val viewPager: ViewPager = view.findViewById(R.id.viewPager)
//
//        // Create an adapter for the ViewPager to display the fragments
//        val pagerAdapter = object : FragmentPagerAdapter(childFragmentManager) {
//            override fun getItem(position: Int): Fragment {
//                // Return the appropriate fragment based on the position
//                return when (position) {
//                    0 -> RecentRestaurantsFragment()
//                    1 -> AllRestaurantsFragment()
//                    else -> throw IllegalArgumentException("Invalid position")
//                }
//            }
//
//            override fun getCount(): Int {
//                // Return the total number of fragments
//                return 2 // Two fragments: Recent Restaurants and All Restaurants
//            }
//
//            override fun getPageTitle(position: Int): CharSequence? {
//                // Return the tab title for each position
//                return when (position) {
//                    0 -> "Recent Restaurants"
//                    1 -> "All Restaurants"
//                    else -> null
//                }
//            }
//        }
//
//        viewPager.adapter = pagerAdapter
//        tabLayout.setupWithViewPager(viewPager)
//
//        return view
//    }
//}


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class HomeFragment : Fragment() {

    private var listener: OnRestaurantSelectedListener? = null

    interface OnRestaurantSelectedListener {
        fun onRestaurantSelected(restaurantId: String)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnRestaurantSelectedListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnRestaurantSelectedListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val tabLayout: TabLayout = view.findViewById(R.id.tabLayout)
        val viewPager: ViewPager = view.findViewById(R.id.viewPager)

        val pagerAdapter = object : FragmentPagerAdapter(childFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            override fun getItem(position: Int): Fragment {
                return when (position) {
                    0 -> RecentRestaurantsFragment()
                    1 -> AllRestaurantsFragment()
                    else -> throw IllegalArgumentException("Invalid position")
                }
            }

            override fun getCount(): Int = 2 // Two fragments

            override fun getPageTitle(position: Int): CharSequence {
                return when (position) {
                    0 -> "Recent"
                    1 -> "All"
                    else -> ""
                }
            }
        }

        viewPager.adapter = pagerAdapter
        tabLayout.setupWithViewPager(viewPager)

        return view
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    // Utility function to communicate with MainActivity
    fun notifyRestaurantSelected(restaurantId: String) {
        listener?.onRestaurantSelected(restaurantId)
    }
}
