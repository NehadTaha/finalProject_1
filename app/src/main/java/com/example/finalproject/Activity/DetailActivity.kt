package com.example.finalproject.Activity

import ManagementCart
import TinyDB
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.finalproject.Data.FoodItem
import com.example.finalproject.R

class DetailActivity : AppCompatActivity() {
    private lateinit var addToCartBtn: Button
    private lateinit var plusBtn: TextView
    private lateinit var minusBtn: TextView
    private lateinit var titleTxt: TextView
    private lateinit var feeTxt: TextView
    private lateinit var descriptionTxt: TextView
    private lateinit var numberOrderTxt: TextView
    private lateinit var startTxt: TextView
    private lateinit var caloryTxt: TextView
    private lateinit var timeTxt: TextView
    private lateinit var picFood: ImageView

    private var numberOrder = 1
    private lateinit var managementCart: ManagementCart
    private lateinit var tinyDB: TinyDB
    private lateinit var foodItem:FoodItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        managementCart = ManagementCart(this)
        tinyDB = TinyDB(this)
        initView()
        getBundle()
    }

    private fun getBundle() {
        foodItem = intent.getSerializableExtra("object") as FoodItem
        val drawableResourceId = resources.getIdentifier(foodItem.picUrl, "drawable", packageName)
        Glide.with(this)
            .load(drawableResourceId)
            .into(picFood)
        titleTxt.text = foodItem.title
        feeTxt.text = "$${foodItem.price}"
        descriptionTxt.text = foodItem.description
        numberOrderTxt.text = numberOrder.toString()
        caloryTxt.text = "${foodItem.energy} Cal"
        startTxt.text = foodItem.score.toString()
        timeTxt.text = "${foodItem.time} min"
        addToCartBtn.text = "Add to cart - $${Math.round(numberOrder * foodItem.price)}"

        plusBtn.setOnClickListener {
            numberOrder++
            numberOrderTxt.text = numberOrder.toString()
            addToCartBtn.text = "Add to cart - $${Math.round(numberOrder * foodItem.price)}"
        }

        minusBtn.setOnClickListener {
            if (numberOrder > 1) {
                numberOrder--
                numberOrderTxt.text = numberOrder.toString()
                addToCartBtn.text = "Add to cart - $${Math.round(numberOrder * foodItem.price)}"
            }
        }

        addToCartBtn.setOnClickListener {
            foodItem.numberinCart = numberOrder
            managementCart.insertFood(foodItem)
            // Add logic here to handle adding item to the cart
        }
    }

    private fun initView() {
        addToCartBtn = findViewById(R.id.addToCartBtn)
        timeTxt = findViewById(R.id.timeTxt)
        feeTxt = findViewById(R.id.priceTxt)
        titleTxt = findViewById(R.id.titleTxt)
        descriptionTxt = findViewById(R.id.descriptionTxt)
        numberOrderTxt = findViewById(R.id.numberitemTxt)
        plusBtn = findViewById(R.id.plusCardBtn)
        minusBtn = findViewById(R.id.minusCartBtn)
        picFood = findViewById(R.id.foodPic)
        startTxt = findViewById(R.id.StarTxt)
        caloryTxt = findViewById(R.id.calTxt)
    }
}
