package com.example.DeliveryFood.DB

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelperFood(context: Context): SQLiteOpenHelper(context,
    DB.TABLE_FOOD, null,
    DB.DATABASE_VERSION
){
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(DB.CREATE_TABLE_FOOD)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(DB.SQL_DELETE_TABLE_FOOD)
        onCreate(db)
    }
}