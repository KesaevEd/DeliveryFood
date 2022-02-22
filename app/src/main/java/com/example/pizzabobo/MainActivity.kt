package com.example.pizzabobo

import android.app.AlertDialog
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuItemCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pizzabobo.Adapter.BannersAdapter
import com.example.pizzabobo.Adapter.CategoriesAdapter
import com.example.pizzabobo.Adapter.FoodAdapter
import com.example.pizzabobo.Common.Common
import com.example.pizzabobo.DB.DbManagerCategories
import com.example.pizzabobo.DB.DbManagerFood
import com.example.pizzabobo.DbAdapter.CategoriesDbAdapter
import com.example.pizzabobo.DbAdapter.FoodDbAdapter
import com.example.pizzabobo.Interface.RetrofitServices
import com.example.pizzabobo.Model.Banner
import com.example.pizzabobo.Model.Categories
import com.example.pizzabobo.Model.Products
import com.example.pizzabobo.databinding.ActivityMain2Binding
import dmax.dialog.SpotsDialog
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMain2Binding
    lateinit var mService: RetrofitServices
    lateinit var categoriesAdapter: CategoriesAdapter
    lateinit var foodAdapter: FoodAdapter
    lateinit var dialog: AlertDialog
    lateinit var layoutManagerCategories: LinearLayoutManager
    lateinit var layoutManagerFood: LinearLayoutManager

    val dbManagerFood = DbManagerFood(this@MainActivity)
    var foodDbAdapter = FoodDbAdapter(ArrayList())

    val dbManagerCategories = DbManagerCategories(this@MainActivity)
    var categoriesDbAdapter = CategoriesDbAdapter(ArrayList())

    private val bannerAdapter = BannersAdapter()
    private val bannersImageIdList =
        listOf(R.drawable.pngegg, R.drawable.pngegg__1_, R.drawable.pngegg__2_)

    private val cities = arrayOf("Москва", "Санкт-Петербург", "Екатеринбург", "Сочи", "Пермь")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTheme(android.R.style.Theme_Holo_Light_NoActionBar)

        binding = ActivityMain2Binding.inflate(layoutInflater)

        setContentView(binding.root)

        dbManagerFood.openDb()
        dbManagerCategories.openDb()
        initRecViewes()


        binding.apply {

            mService = Common.retrofitService
            rvCategories.setHasFixedSize(true)
            layoutManagerCategories =
                LinearLayoutManager(this@MainActivity, RecyclerView.HORIZONTAL, false)
            rvCategories.layoutManager = layoutManagerCategories

            rvFood.setHasFixedSize(true)
            layoutManagerFood = LinearLayoutManager(this@MainActivity)
            rvFood.layoutManager = layoutManagerFood

            dialog = SpotsDialog.Builder().setCancelable(true).setContext(this@MainActivity).build()

            if (verifyAvailableNetwork(this@MainActivity)) {
                dialog.show()
                getCategories()
                getSandwiches()
                dialog.dismiss()
            } else {
                dialog.show()
                Toast.makeText(this@MainActivity, "no internet", Toast.LENGTH_SHORT).show()
                fillDbAdapters()
                dialog.dismiss()
            }

        }

    }

    override fun onResume() {
        super.onResume()
        dbManagerFood.openDb()
        dbManagerCategories.openDb()
    }


    override fun onDestroy() {
        super.onDestroy()
        dbManagerFood.closeDb()
        dbManagerCategories.closeDb()
    }


    private fun getCategories() {
        binding.apply {
            mService.getMenu().enqueue(object : Callback<Categories> {

                override fun onResponse(
                    call: retrofit2.Call<Categories>,
                    response: Response<Categories>
                ) {
                    categoriesAdapter = CategoriesAdapter(baseContext, response.body()!!.categories)
                    categoriesAdapter.notifyDataSetChanged()
                    rvCategories.adapter = categoriesAdapter

                    val categoriesList = response.body()!!.categories
                    if(dbManagerCategories.checkDbOnFilling()){
                        for(i in categoriesList.indices){
                            dbManagerCategories.updateItem(categoriesList[i].title, i)
                        }
                    }else{
                        for(i in categoriesList.indices){
                            dbManagerCategories.insertToDb(categoriesList[i].title)
                        }
                    }
                }

                override fun onFailure(
                    call: retrofit2.Call<Categories>,
                    t: Throwable
                ) {

                }
            })
        }
    }


    private fun getSandwiches() {
        val dbManagerFood = DbManagerFood(this@MainActivity)
        dbManagerFood.openDb()
        binding.apply {

            mService.getSandwichesList().enqueue(object : Callback<Products> {
                override fun onResponse(
                    call: retrofit2.Call<Products>,
                    response: Response<Products>
                ) {
                    foodAdapter = FoodAdapter(baseContext, response.body()!!.products)
                    foodAdapter.notifyDataSetChanged()
                    rvFood.adapter = foodAdapter


                    val foodList = response.body()!!.products

                    if (dbManagerFood.checkDbOnFilling()) {

                        for (i in 0 until response.body()!!.products.size) {
                            var detailText = foodList[i].detailText
                            val re = Regex("[^%.А-Яа-я0-9 ]")
                            detailText = detailText?.let { re.replace(it, "") }
                            dbManagerFood.updateItem(
                                foodList[i].name,
                                detailText,
                                foodList[i].offers[0].price.toString(),
                                i
                            )
                        }
                    } else {
                        for (i in 0 until response.body()!!.products.size) {
                            var detailText = foodList[i].detailText
                            val re = Regex("[^%.А-Яа-я0-9 ]")
                            detailText = detailText?.let { re.replace(it, "") }
                            dbManagerFood.insertToDb(
                                foodList[i].name,
                                detailText,
                                foodList[i].offers[0].price.toString()
                            )
                        }
                    }

                }

                override fun onFailure(call: retrofit2.Call<Products>, t: Throwable) {

                }

            })
        }
    }


    fun initRecViewes() {
        val rvFood: RecyclerView = findViewById(R.id.rvFood)
        rvFood.layoutManager =
            LinearLayoutManager(this)
        rvFood.adapter = foodDbAdapter
        val rvCategories: RecyclerView = findViewById(R.id.rvCategories)
        rvCategories.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        rvCategories.adapter = categoriesDbAdapter
        binding.apply {
            rvBanners.layoutManager =
                LinearLayoutManager(this@MainActivity, RecyclerView.HORIZONTAL, false)
            rvBanners.adapter = bannerAdapter
            for (element in bannersImageIdList) {
                val banner = Banner(element)
                bannerAdapter.addBanner(banner)
            }

        }
    }

    fun fillDbAdapters() {
        categoriesDbAdapter.updateAdapter(dbManagerCategories.readDbData())
        foodDbAdapter.updateAdapter(dbManagerFood.readDbData())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.custom_menu, menu)

        val item = menu!!.findItem(R.id.city)
        val spinner = MenuItemCompat.getActionView(item) as Spinner

        citySpinner(spinner, cities)
        return true
    }

    private fun citySpinner(spinner: Spinner, cities: Array<String>) {
        spinner.adapter = ArrayAdapter(this, R.layout.spinner_custom_text, cities)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    fun verifyAvailableNetwork(activity: AppCompatActivity): Boolean {
        val connectivityManager =
            activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}