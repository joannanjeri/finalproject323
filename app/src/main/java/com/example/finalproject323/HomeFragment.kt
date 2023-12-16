package com.example.finalproject323

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

/**
 * home fragment containing tabs for recent and all restaurants
 */
class HomeFragment : Fragment() {

    private var listener: OnRestaurantSelectedListener? = null

    /**
     * listener interface for restaurant selection events
     */
    interface OnRestaurantSelectedListener {
        fun onRestaurantSelected(restaurantId: String)
    }

    /**
     * attaches the fragment to the context and initializes the listener
     */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnRestaurantSelectedListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnRestaurantSelectedListener")
        }
    }

    /**
     * creates the view for the fragment
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val tabLayout: TabLayout = view.findViewById(R.id.tabLayout)
        val viewPager: ViewPager = view.findViewById(R.id.viewPager)

        /**
         * pager adapter to manage fragments in viewpager
         */
        val pagerAdapter = object : FragmentPagerAdapter(childFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            override fun getItem(position: Int): Fragment {
                return when (position) {
                    0 -> RecentRestaurantsFragment()
                    1 -> AllRestaurantsFragment()
                    else -> throw IllegalArgumentException("Invalid position")
                }
            }

            override fun getCount(): Int = 2

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

    /**
     * cleans up the listener when the fragment is detached
     */
    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * notifies the listener when a restaurant is selected
     */
    fun notifyRestaurantSelected(restaurantId: String) {
        listener?.onRestaurantSelected(restaurantId)
    }
}
