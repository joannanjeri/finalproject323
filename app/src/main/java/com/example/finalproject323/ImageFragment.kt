package com.example.finalproject323

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

/**
 * fragment for displaying a single image
 */
class ImageFragment : Fragment() {
    private var imageUrl: String? = null

    /**
     * initializes the fragment
     * retrieves the image url from the arguments
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
         * creates a new instance of the image fragment with the given image url
         * @param imageUrl the url of the image to display
         * @return a new instance of image fragment
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
