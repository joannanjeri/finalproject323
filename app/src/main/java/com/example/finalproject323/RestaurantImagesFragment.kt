package com.example.finalproject323

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide

/**
 * fragment for displaying restaurant images in a viewpager2
 */
class RestaurantImagesFragment : Fragment() {
    private lateinit var viewPager: ViewPager2
    private var imageUrls: List<String> = listOf()

    /**
     * initializes the fragment and retrieves image urls from arguments
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            imageUrls = it.getStringArrayList(ARG_IMAGE_URLS) ?: listOf()
        }
    }

    /**
     * creates the view for the fragment
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_restaurant_images, container, false)
    }

    /**
     * called after the view is created
     * sets up the viewpager2 and its adapter
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager = view.findViewById(R.id.viewPagerRestaurantImages)
        viewPager.adapter = ImagePagerAdapter(this, imageUrls)
    }

    companion object {
        private const val ARG_IMAGE_URLS = "image_urls"

        /**
         * creates a new instance of restaurantimagesfragment with a list of image urls
         */
        fun newInstance(imageUrls: List<String>): RestaurantImagesFragment {
            val fragment = RestaurantImagesFragment()
            val args = Bundle()
            args.putStringArrayList(ARG_IMAGE_URLS, ArrayList(imageUrls))
            fragment.arguments = args
            return fragment
        }
    }

    /**
     * adapter for viewpager2 to display images
     */
    private inner class ImagePagerAdapter(
        fragment: Fragment,
        private val imageUrls: List<String>
    ) : FragmentStateAdapter(fragment) {

        /**
         * returns the number of items in the adapter
         */
        override fun getItemCount(): Int = imageUrls.size

        /**
         * creates a fragment for displaying an image at the specified position
         */
        override fun createFragment(position: Int): Fragment {
            return ImageFragment.newInstance(imageUrls[position])
        }
    }

    /**
     * fragment for displaying a single image
     */
    class ImageFragment : Fragment() {
        private var imageUrl: String? = null

        /**
         * initializes the fragment and retrieves the image url from arguments
         */
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            arguments?.let {
                imageUrl = it.getString(ARG_IMAGE_URL)
            }
        }

        /**
         * creates the view for the fragment
         */
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            return inflater.inflate(R.layout.fragment_image, container, false)
        }

        /**
         * called after the view is created
         * sets up the image view and loads the image using glide
         */
        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            val imageView: ImageView = view.findViewById(R.id.imageView)
            imageUrl?.let {
                Glide.with(this)
                    .load(it)
                    .into(imageView)
            }
        }

        companion object {
            private const val ARG_IMAGE_URL = "image_url"

            /**
             * creates a new instance of imagefragment with the given image url
             */
            fun newInstance(imageUrl: String): ImageFragment {
                val fragment = ImageFragment()
                val args = Bundle()
                args.putString(ARG_IMAGE_URL, imageUrl)
                fragment.arguments = args
                return fragment
            }
        }
    }
}
