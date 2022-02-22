package com.example.pizzabobo.DB

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class DbManagerCategories(context: Context) {

    val DbHelper = DbHelperCategories(context)
    var db: SQLiteDatabase? = null

    fun openDb(){
        db = DbHelper.writableDatabase
    }

    fun insertToDb(name: String?){
        val values = ContentValues().apply{
            put(DB.COLUMN_NAME_NAME, name)
        }
        db?.insert(DB.TABLE_CATEGORIES, null, values)
    }


    fun readDbData(): ArrayList<ListItemCategories>{
        val dataList = ArrayList<ListItemCategories>()
        val cursor = db?.query(DB.TABLE_CATEGORIES, null, null, null, null, null, null)
        while(cursor?.moveToNext()!!){
            val dataName = cursor.getString(cursor.getColumnIndexOrThrow(DB.COLUMN_NAME_NAME))
            val item = ListItemCategories()
            item.name = dataName
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

    fun updateItem(name: String?, id: Int){
        val selection = BaseColumns._ID + "=$id"
        val values = ContentValues().apply{
            put(DB.COLUMN_NAME_NAME, name)
        }
        db?.update(DB.TABLE_CATEGORIES, values, selection, null)
    }
}