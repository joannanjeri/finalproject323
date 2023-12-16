package com.example.finalproject323

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

/**
 * main activity of the application
 * manages navigation drawer and fragment transactions
 */
class MainActivity : AppCompatActivity(), HomeFragment.OnRestaurantSelectedListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var toolbar: Toolbar

    /**
     * initializes the activity
     * sets up the navigation drawer and user interface
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser == null) {
            navigateToSignInScreen()
        } else {
            setContentView(R.layout.activity_main)
            setupNavigationDrawer()
            updateUI(currentUser)

            if (savedInstanceState == null) {
                drawerLayout.openDrawer(GravityCompat.START)

                val homeFragment = HomeFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.content_frame, homeFragment)
                    .commit()
            }
        }
    }

    /**
     * sets up the navigation drawer with listeners for menu item selection
     */
    private fun setupNavigationDrawer() {
        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.navView)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, R.string.drawer_open, R.string.drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener { menuItem ->
            drawerLayout.closeDrawer(GravityCompat.START)

            when (menuItem.itemId) {
                R.id.navHome -> {
                    val homeFragment = HomeFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.content_frame, homeFragment)
                        .commit()
                }
                R.id.navRecentOrders -> {
                    val intent = Intent(this, RecentOrders::class.java)
                    startActivity(intent)
                }
                R.id.navCalendarView -> {

                }
                R.id.navSignOut -> {
                    FirebaseAuth.getInstance().signOut()
                    navigateToSignInScreen()
                }
            }
            true
        }
    }

    /**
     * navigates to the sign-in screen
     */
    private fun navigateToSignInScreen() {
        val intent = Intent(this, SignUpScreen::class.java)
        startActivity(intent)
        finish()
    }

    /**
     * updates the user interface with the current user's information
     * @param user the currently logged-in user
     */
    private fun updateUI(user: FirebaseUser) {
        val headerView = navView.getHeaderView(0)
        val userNameTextView = headerView.findViewById<TextView>(R.id.headerNameTextView)
        val userEmailTextView = headerView.findViewById<TextView>(R.id.headerEmailTextView)
        userNameTextView.text = user.displayName ?: "No Name"
        userEmailTextView.text = user.email ?: "No Email"
    }

    /**
     * fetches images for a specific restaurant
     * @param restaurantId the id of the restaurant
     * @param callback callback function to handle the fetched image urls
     */
    private fun fetchImagesForRestaurant(restaurantId: String, callback: (List<String>) -> Unit) {
        val db = FirebaseFirestore.getInstance()
        db.collection("restaurants").document(restaurantId)
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val restaurant = document.toObject(Restaurant::class.java)
                    callback(restaurant?.imageUrls ?: emptyList())
                } else {
                }
            }
            .addOnFailureListener { exception ->
            }
    }

    /**
     * handles restaurant selection from the home fragment
     * @param restaurantId the id of the selected restaurant
     */
    override fun onRestaurantSelected(restaurantId: String) {
        fetchImagesForRestaurant(restaurantId) { imageUrls ->
            // Use the image URLs directly
            val restaurantImagesFragment = RestaurantImagesFragment.newInstance(imageUrls)
            supportFragmentManager.beginTransaction()
                .replace(R.id.content_frame, restaurantImagesFragment)
                .addToBackStack(null)
                .commit()
        }
    }

    /**
     * handles back button press to close the navigation drawer if open
     */
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
