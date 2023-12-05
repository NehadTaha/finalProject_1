package com.example.finalproject.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.Adapter.FoodListAdapter
import com.example.finalproject.Domain.FoodDomain
import com.example.finalproject.R

class MainActivity : AppCompatActivity() {
    private lateinit var adapterFoodList: RecyclerView.Adapter<*>
    private lateinit var recyclerViewFood: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        val items = ArrayList<FoodDomain>()
        items.add(
            FoodDomain(
                "Cheese Burger",
                "Satisfy your cravings with our juicy Cheese Burger. \n" +
                        "Served with crispy fries and a drink",
                "fast_1",
                15.0,
                20,
                120,
                4.0,
                0
            )
        )
        items.add(
            FoodDomain(
                "Pizza Pepperoni",
                "Get the taste of Italy with our delicious Pepperoni Pizza",
                "fast_2",
                10.0,
                25,
                200,
                5.0,
                0
            )
        )
        items.add(
            FoodDomain(
                "Vegetable Pizza",
                "Looking for a healthier option? Try our vegetable Pizza",
                "fast_3",
                13.0,
                30,
                100,
                4.5,
                0
            )
        )

        recyclerViewFood = findViewById(R.id.view1) as RecyclerView // Replace 'view1' with your RecyclerView ID
        recyclerViewFood.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        adapterFoodList = FoodListAdapter(items) // Replace 'FoodListAdapter(items)' with your actual adapter initialization
        recyclerViewFood.adapter = adapterFoodList
    }
}
