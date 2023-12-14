package com.example.finalproject.Activity

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.Adapter.FoodListAdapter
import com.example.finalproject.Data.AppDatabase
import com.example.finalproject.Data.FoodItem
import com.example.finalproject.R
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var adapterFoodList: RecyclerView.Adapter<*>
    private lateinit var recyclerViewFood: RecyclerView
    private lateinit var db: AppDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = AppDatabase.getInstance(this)
        initRecyclerView()
        navigationToSupport();
        navigationToCart();
        bottomNavigation();
        val settingsBtn: LinearLayout = findViewById(R.id.settingsBtn)
        settingsBtn.setOnClickListener { view ->
            showSettingsMenu(view)
        }
        val cartBtn: LinearLayout = findViewById(R.id.cartBtn)
        cartBtn.setOnClickListener {
            // Create an intent to start the CartActivity
            val intent = Intent(this@MainActivity, CartActivity::class.java)
            startActivity(intent) // Start the CartActivity
        }

    }

    private fun bottomNavigation() {
        val homeBtn: LinearLayout = findViewById(R.id.homeBtn)
        val cartBtn: LinearLayout = findViewById(R.id.cartBtn)

        homeBtn.setOnClickListener {
            startActivity(Intent(this@MainActivity, MainActivity::class.java))
        }

        cartBtn.setOnClickListener {
            startActivity(Intent(this@MainActivity, CartActivity::class.java))
        }
    }
    private fun navigationToCart() {
        val supportBtn: LinearLayout = findViewById(R.id.cartBtn)
        supportBtn.setOnClickListener {
            startActivity(Intent(this@MainActivity, CartActivity::class.java))
        }

    }

    private fun navigationToSupport() {
        val supportBtn: LinearLayout = findViewById(R.id.supportBtn)
        supportBtn.setOnClickListener {
            startActivity(Intent(this@MainActivity, SupportActivity::class.java))
        }

    }
    private fun initRecyclerView() {
        val items = ArrayList<FoodItem>()
        items.add(
            FoodItem(
                UUID.randomUUID().toString(),
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
            FoodItem(
                UUID.randomUUID().toString(),
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
            FoodItem(
                UUID.randomUUID().toString(),
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
    private fun showSettingsMenu(view: View) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.inflate(R.menu.settings_menu)

        popupMenu.setOnMenuItemClickListener { item: MenuItem ->
            when (item.itemId) {
                R.id.menu_time_settings -> {
                    showTimePickerDialog()
                    true
                }
                R.id.menu_date_settings -> {
                    showDatePickerDialog()
                    true
                }
                else -> false
            }
        }
        popupMenu.show()
    }

    private fun showTimePickerDialog() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            this,
            TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                // Handle the selected time
                // For example, save it or perform actions with it
            },
            hour,
            minute,
            true
        )
        timePickerDialog.show()
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                // Handle the selected date
                // For example, save it or perform actions with it
            },
            year,
            month,
            dayOfMonth
        )
        datePickerDialog.show()
    }
    override fun onDestroy() {
        super.onDestroy()
        // Close the database connection when the Activity is destroyed
        db.close()
    }
}
