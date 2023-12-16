package com.example.finalproject323

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * adapter for viewpager2 to display images in fragments
 * @param fragment the fragment that hosts the viewpager2
 * @param imageUrls list of image urls to display in the viewpager2
 */
class ImagePagerAdapter(
    fragment: Fragment,
    private val imageUrls: List<String>
) : FragmentStateAdapter(fragment) {

    /**
     * returns the total number of items in the adapter
     * @return size of the image urls list
     */
    override fun getItemCount(): Int = imageUrls.size

    /**
     * creates the fragment for the given page position
     * @param position the position of the item within the adapter's data set
     * @return a new instance of image fragment for the specified position
     */
    override fun createFragment(position: Int): Fragment {
        return ImageFragment.newInstance(imageUrls[position])
    }
}

