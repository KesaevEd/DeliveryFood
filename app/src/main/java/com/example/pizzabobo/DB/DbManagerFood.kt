package com.example.pizzabobo.DB

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import com.example.pizzabobo.Model.Products

class DbManagerFood(context: Context) {

    val DbHelper = DbHelperFood(context)
    var db: SQLiteDatabase? = null

    fun openDb(){
        db = DbHelper.writableDatabase
    }

    fun insertToDb(name: String?, description: String?, price: String?){
        val values = ContentValues().apply{
            put(DB.COLUMN_NAME_NAME, name)
            put(DB.COLUMN_NAME_DESCRIPTION, description)
            put(DB.COLUMN_NAME_PRICE, price)
        }
        db?.insert(DB.TABLE_FOOD, null, values)
    }


    fun readDbData(): ArrayList<ListItemFood>{
        val dataList = ArrayList<ListItemFood>()
        val cursor = db?.query(DB.TABLE_FOOD, null, null, null, null, null, null)
        while(cursor?.moveToNext()!!){
            val dataName = cursor.getString(cursor.getColumnIndexOrThrow(DB.COLUMN_NAME_NAME))
            val dataDescription = cursor.getString(cursor.getColumnIndexOrThrow(DB.COLUMN_NAME_DESCRIPTION))
            val dataPrice = cursor.getString(cursor.getColumnIndexOrThrow(DB.COLUMN_NAME_PRICE))
            val item = ListItemFood()
            item.name = dataName
            item.description = dataDescription
            item.price = dataPrice
            dataList.add(item)
        }
        cursor.close()
        return dataList
    }

    fun checkDbOnFilling(): Boolean{
        return !readDbData().isEmpty()
    }

    fun closeDb(){
        DbHelper.close()
    }

    fun updateItem(name: String?, description: String?,  price: String?, id: Int){
        val selection = BaseColumns._ID + "=$id"
        val values = ContentValues().apply{
            put(DB.COLUMN_NAME_NAME, name)
            put(DB.COLUMN_NAME_DESCRIPTION, description)
            put(DB.COLUMN_NAME_PRICE, price)
        }
        db?.update(DB.TABLE_FOOD, values, selection, null)
    }
}