package com.example.DeliveryFood.DB

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelperCategories(context: Context): SQLiteOpenHelper(context,
    DB.TABLE_CATEGORIES, null,
    DB.DATABASE_VERSION
) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(DB.CREATE_TABLE_CATEGORIES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(DB.SQL_DELETE_TABLE_CATEGORIES)
        onCreate(db)
    }
}