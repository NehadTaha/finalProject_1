package com.example.finalproject.Activity

import ManagementCart
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.Helper.ChangeNumberItemsListener
import com.example.finalproject.Adapter.CartListAdapter
import com.example.finalproject.R

class CartActivity : AppCompatActivity(), ChangeNumberItemsListener {
    private lateinit var adapter: CartListAdapter
    private lateinit var recyclerViewList: RecyclerView
    private lateinit var managementCart: ManagementCart
    private lateinit var totalFeeTxt: TextView
    private lateinit var taxTxt: TextView
    private lateinit var deliveryTxt: TextView
    private lateinit var totalTxt: TextView
    private lateinit var emptyTxt: TextView
    private var tax: Double = 0.0
    private lateinit var scrollView: ScrollView
    private lateinit var backBtn: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        managementCart = ManagementCart(this)

        initView()
        initList()
        setVariable()
    }

    override fun changed() {
        calculateCart()
       // refreshCart() // Added: Refresh the cart after any change in items
    }

    private fun refreshCart() {
        initList() // Refresh the cart by reinitializing the list
    }

    private fun setVariable() {
        backBtn.setOnClickListener { finish() }
    }

    private fun initList() {
        recyclerViewList = findViewById(R.id.view3)
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerViewList.layoutManager = linearLayoutManager

        managementCart.getListCart { cartList ->
            runOnUiThread {
                adapter = CartListAdapter(cartList, this, this)
                recyclerViewList.adapter = adapter

                if (cartList.isEmpty()) {
                    emptyTxt.visibility = View.VISIBLE
                    scrollView.visibility = View.GONE
                } else {
                    emptyTxt.visibility = View.GONE
                    scrollView.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun calculateCart() {
        managementCart.getTotalFee { cartTotal ->
            val percentTax = 0.02
            val delivery = 10.0
            tax = Math.round(cartTotal * percentTax * 100.0) / 100.0

            val total = Math.round(cartTotal + tax + delivery * 100.0) / 100
            val itemTotal = Math.round(cartTotal * 100.0) / 100.0

            totalFeeTxt.text = "$$itemTotal"
            taxTxt.text = "$$tax"
            deliveryTxt.text = "$$delivery"
            totalTxt.text = "$$total"
        }
    }

    private fun initView() {
        totalFeeTxt = findViewById(R.id.totalFeeTxt)
        taxTxt = findViewById(R.id.taxTxt)
        deliveryTxt = findViewById(R.id.deliveryTxt)
        totalTxt = findViewById(R.id.totalTxt)
        scrollView = findViewById(R.id.scrollView)
        backBtn = findViewById(R.id.backBtn)
        emptyTxt = findViewById(R.id.emptyTxt)
    }
}
