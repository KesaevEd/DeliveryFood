package com.example.DeliveryFood.DB

import android.provider.BaseColumns

object DB {

    const val TABLE_FOOD = "Food"
    const val TABLE_CATEGORIES = "Categories"
    const val COLUMN_NAME_NAME = "name"
    const val COLUMN_NAME_DESCRIPTION = "description"
    const val COLUMN_NAME_PRICE = "price"

    const val DATABASE_VERSION = 1
    const val DATABASE_NAME = "PizzaBoBo.db"

    const val CREATE_TABLE_FOOD = "CREATE TABLE IF NOT EXISTS $TABLE_FOOD (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY, $COLUMN_NAME_NAME TEXT, $COLUMN_NAME_DESCRIPTION TEXT, $COLUMN_NAME_PRICE TEXT)"

    const val CREATE_TABLE_CATEGORIES = "CREATE TABLE IF NOT EXISTS $TABLE_CATEGORIES (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY, $COLUMN_NAME_NAME TEXT)"


    const val SQL_DELETE_TABLE_FOOD = "DROP TABLE IF EXISTS $TABLE_FOOD"
    const val SQL_DELETE_TABLE_CATEGORIES = "DROP TABLE IF EXISTS $TABLE_CATEGORIES"
}