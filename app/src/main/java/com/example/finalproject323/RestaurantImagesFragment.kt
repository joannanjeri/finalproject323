//package com.example.finalproject323
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import androidx.viewpager2.widget.ViewPager2
//
//class RestaurantImagesFragment : Fragment() {
//    private lateinit var viewPager: ViewPager2
//    // Dummy list of images, replace with actual image IDs from your database
//    private val imageList = listOf(R.drawable.chipotle, R.drawable.chipotle, R.drawable.chipotle)
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_restaurant_images, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        viewPager = view.findViewById(R.id.viewPagerRestaurantImages)
//        viewPager.adapter = ImagePagerAdapter(this, imageList)
//    }
//}


package com.example.finalproject323

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2

class RestaurantImagesFragment : Fragment() {
    private lateinit var viewPager: ViewPager2
    private var imageList = listOf(R.drawable.chipotle, R.drawable.chipotle, R.drawable.chipotle) // Default list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            // Here, retrieve and handle the passed arguments
            // For example, if you pass a list of image IDs:
            // imageList = it.getIntegerArrayList(ARG_IMAGE_LIST)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_restaurant_images, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager = view.findViewById(R.id.viewPagerRestaurantImages)
        viewPager.adapter = ImagePagerAdapter(this, imageList)
    }

    companion object {
        // Use constants to define argument keys
        private const val ARG_IMAGE_LIST = "image_list"

        // newInstance method to create instances of RestaurantImagesFragment
        fun newInstance(imageList: List<Int>): RestaurantImagesFragment {
            val fragment = RestaurantImagesFragment()
            val args = Bundle()
            args.putIntegerArrayList(ARG_IMAGE_LIST, ArrayList(imageList))
            fragment.arguments = args
            return fragment
        }
    }
}
