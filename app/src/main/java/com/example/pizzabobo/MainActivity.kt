package com.example.pizzabobo

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuItemCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pizzabobo.databinding.ActivityMain2Binding
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMain2Binding

    private val bannerAdapter = BannersAdapter()
    private val bannersImageIdList =
        listOf(R.drawable.pngegg, R.drawable.pngegg__1_, R.drawable.pngegg__2_)

    private val categoriesAdapter = CategoriesAdapter()
    private val categoriesList = listOf("Пицца", "Комбо", "Напитки", "Десерт")

    private val foodAdapter = FoodAdapter()
    private val foodImageIdList =
        listOf(R.drawable.pizza1, R.drawable.pizza2, R.drawable.pizza3, R.drawable.pizza4, R.drawable.combo1, R.drawable.combo2, R.drawable.combo3)
    private val foodTitleList =
        listOf("Ветчина и грибы", "Баварские колбаски", "Нежный лосось", "Четыре сыра", "Сытный обед", "Быстрый ланч", "Быстрый ланч 2")
    private val foodDescriptionList = listOf(
        "Ветчина, помидоры, увеличенная порция мацареллы, томатный соус",
        "Ветчина, баварские колбаски, пикантная пеперони, томатный соус",
        "Лосось, оливки, соус чедер",
        "Ветчина, помидоры, увеличенная порция мацареллы, сыр эмменталь, сыр горгонзола, сыр пармезан",
        "Шашлык, картошка фри, овощи", "Бургер, кола, картоха", "Бурито, кола, картошка"
    )
    private val foodPriceList = listOf("от 345 р", "от 345 р", "от 345 р", "от 345 р", "от 420 р", "от 230 р", "от 230 р")

    private val cities = arrayOf("Москва", "Санкт-Петербург", "Екатеринбург", "Сочи", "Пермь")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTheme(android.R.style.Theme_Holo_Light_NoActionBar)

        binding = ActivityMain2Binding.inflate(layoutInflater)

        setContentView(binding.root)
        init()

        binding.butNav.setOnNavigationItemReselectedListener {
            when(it.itemId){
                R.id.navigation_menu ->{}
                R.id.navigation_profile ->{Toast.makeText(this, "Отсутствует подключение к интернету", Toast.LENGTH_SHORT).show()}
                R.id.navigation_basket ->{Toast.makeText(this, "Отсутствует подключение к интернету", Toast.LENGTH_SHORT).show()}
            }
        }
    }


    private fun init() {
        binding.apply {
            rvBanners.layoutManager =
                LinearLayoutManager(this@MainActivity, RecyclerView.HORIZONTAL, false)
            rvBanners.adapter = bannerAdapter
            for (i in 0 until bannersImageIdList.size) {
                val banner = Banner(bannersImageIdList[i])
                bannerAdapter.addBanner(banner)
            }
            rvCategories.layoutManager =
                LinearLayoutManager(this@MainActivity, RecyclerView.HORIZONTAL, false)
            rvCategories.adapter = categoriesAdapter
            for (i in 0 until categoriesList.size) {
                val category = Category(categoriesList[i])
                categoriesAdapter.addCategory(category)
            }

            rvFood.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            rvFood.adapter = foodAdapter
            for (i in 0 until foodTitleList.size) {
                val food = Food(
                    foodImageIdList[i],
                    foodTitleList[i],
                    foodDescriptionList[i],
                    foodPriceList[i]
                )
                foodAdapter.addFood(food)
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.custom_menu, menu)

        val item = menu!!.findItem(R.id.city)
        val spinner = MenuItemCompat.getActionView(item) as Spinner

        citySpinner(spinner, cities)
        return true
    }

    private fun citySpinner(spinner: Spinner, cities: Array<String>){
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
}