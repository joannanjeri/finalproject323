//package com.example.finalproject323
//
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.widget.TextView
//import android.widget.Toolbar
//import androidx.appcompat.app.ActionBarDrawerToggle
//import androidx.core.view.GravityCompat
//import androidx.drawerlayout.widget.DrawerLayout
//import com.google.android.material.navigation.NavigationView
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.auth.FirebaseUser
//import com.google.firebase.firestore.FirebaseFirestore
//
//
//class MainActivity : AppCompatActivity(), HomeFragment.OnRestaurantSelectedListener {
//
//    private lateinit var drawerLayout: DrawerLayout
//    private lateinit var navView: NavigationView
//    private lateinit var toolbar: Toolbar
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        val currentUser = FirebaseAuth.getInstance().currentUser
//        if (currentUser == null) {
//            navigateToSignInScreen()
//        } else {
//            setContentView(R.layout.activity_main)
//            setupNavigationDrawer()
//            updateUI(currentUser)
//
//            if (savedInstanceState == null) {
//                drawerLayout.openDrawer(GravityCompat.START)
//
//                val homeFragment = HomeFragment()
//                supportFragmentManager.beginTransaction()
//                    .replace(R.id.content_frame, homeFragment)
//                    .commit()
//            }
//        }
//    }
//
//    private fun setupNavigationDrawer() {
//        drawerLayout = findViewById(R.id.drawer_layout)
//        navView = findViewById(R.id.navView)
//
//        val toggle = ActionBarDrawerToggle(
//            this, drawerLayout, R.string.drawer_open, R.string.drawer_close
//        )
//        drawerLayout.addDrawerListener(toggle)
//        toggle.syncState()
//
//        navView.setNavigationItemSelectedListener { menuItem ->
//            drawerLayout.closeDrawer(GravityCompat.START)
//
//            when (menuItem.itemId) {
//                R.id.navHome -> {
//                    val homeFragment = HomeFragment()
//                    supportFragmentManager.beginTransaction()
//                        .replace(R.id.content_frame, homeFragment)
//                        .commit()
//                }
//                R.id.navRecentOrders -> {
//                    val intent = Intent(this, RecentOrders::class.java)
//                    startActivity(intent)
//                }
//                R.id.navCalendarView -> {
//                    // Handle "Calendar View" navigation
//                }
//                R.id.navSignOut -> {
//                    // Handle "Sign Out" action
//                    FirebaseAuth.getInstance().signOut()
//                    navigateToSignInScreen()
//                }
//            }
//            true
//        }
//    }
//
//    private fun navigateToSignInScreen() {
//        val intent = Intent(this, SignUpScreen::class.java)
//        startActivity(intent)
//        finish()
//    }
//
//    private fun updateUI(user: FirebaseUser) {
//        val headerView = navView.getHeaderView(0)
//        val userNameTextView = headerView.findViewById<TextView>(R.id.headerNameTextView)
//        val userEmailTextView = headerView.findViewById<TextView>(R.id.headerEmailTextView)
//        userNameTextView.text = user.displayName ?: "No Name"
//        userEmailTextView.text = user.email ?: "No Email"
//    }
//
//    private fun fetchImagesForRestaurant(restaurantId: String, callback: (List<String>) -> Unit) {
//        val db = FirebaseFirestore.getInstance()
//        db.collection("restaurants").document(restaurantId)
//            .get()
//            .addOnSuccessListener { document ->
//                if (document.exists()) {
//                    val restaurant = document.toObject(Restaurant::class.java)
//                    callback(restaurant?.imageUrls ?: emptyList())
//                } else {
//                    // Handle the case where the restaurant doesn't exist
//                }
//            }
//            .addOnFailureListener { exception ->
//                // Handle any errors here
//            }
//    }
//
//    override fun onRestaurantSelected(restaurantId: String) {
//        fetchImagesForRestaurant(restaurantId) { imageUrls ->
//            // Now you have the image URLs
//
//            // Assuming RestaurantImagesFragment is expecting a list of image resource IDs
//            // If it expects URLs, you can directly use imageUrls
//            val imageResIds = convertUrlsToDrawableIds(imageUrls) // Convert URLs to drawable resource IDs
//
//            val restaurantImagesFragment = RestaurantImagesFragment.newInstance(imageResIds)
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.content_frame, restaurantImagesFragment)
//                .addToBackStack(null)
//                .commit()
//        }
//    }
//
//    override fun onBackPressed() {
//        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
//            drawerLayout.closeDrawer(GravityCompat.START)
//        } else {
//            super.onBackPressed()
//        }
//    }
//}
