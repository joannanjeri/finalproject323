package com.example.finalproject323

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class RecentOrders : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recent_orders)

        listView = findViewById(R.id.recentOrdersListView)
        databaseHelper = DatabaseHelper(this)

        displayRecentOrders()
    }

    private fun displayRecentOrders() {
        val ordersList = databaseHelper.getRecentOrders()

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            ordersList.map { order -> "${order.name} - ${order.date}" }
        )
        listView.adapter = adapter
    }
}
