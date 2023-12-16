package com.example.finalproject323

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * helper class for managing database creation and version management
 * @property context the context where the database should be created
 */
class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    /**
     * called when the database is created for the first time
     * @param db the database
     */
    override fun onCreate(db: SQLiteDatabase) {
        val createOrderTable = """
            CREATE TABLE $TABLE_ORDERS (
                $COLUMN_ID INTEGER PRIMARY KEY,
                $COLUMN_NAME TEXT,
                $COLUMN_DATE TEXT
            )
            """.trimIndent()
        db.execSQL(createOrderTable)
    }

    /**
     * called when the database needs to be upgraded
     * @param db the database
     * @param oldVersion the old database version
     * @param newVersion the new database version
     */
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_ORDERS")
        onCreate(db)
    }

    /**
     * retrieves a list of recent orders from the database
     * @return list of orders
     */
    fun getRecentOrders(): List<Order> {
        val orders = mutableListOf<Order>()
        val db = this.readableDatabase
        val selectQuery = "SELECT * FROM $TABLE_ORDERS ORDER BY $COLUMN_DATE DESC"
        val cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()) {
            val idIndex = cursor.getColumnIndex(COLUMN_ID)
            val nameIndex = cursor.getColumnIndex(COLUMN_NAME)
            val dateIndex = cursor.getColumnIndex(COLUMN_DATE)

            if (idIndex != -1 && nameIndex != -1 && dateIndex != -1) {
                do {
                    val order = Order(
                        cursor.getInt(idIndex),
                        cursor.getString(nameIndex),
                        cursor.getString(dateIndex)
                    )
                    orders.add(order)
                } while (cursor.moveToNext())
            }
        }
        cursor.close()
        return orders
    }

    companion object {
        private const val DATABASE_NAME = "OrderDatabase"
        private const val DATABASE_VERSION = 1

        private const val TABLE_ORDERS = "orders"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_DATE = "date"
    }

}