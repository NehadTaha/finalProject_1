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
import com.example.finalproject.Manager.ManagementCart
import com.example.finalproject.R

class CartActivity : AppCompatActivity() {
    private lateinit var adapter: RecyclerView.Adapter<*>
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

    private fun setVariable() {
        backBtn.setOnClickListener { finish() }
    }

    private fun initList() {
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerViewList.layoutManager = linearLayoutManager
        adapter = CartListAdapter(managementCart.getListCart(), this, object : ChangeNumberItemsListener {
            override fun changed() {
                calculateCart()
            }
        })

        recyclerViewList.adapter = adapter

        if (managementCart.getListCart().isEmpty()) {
            emptyTxt.visibility = View.VISIBLE
            scrollView.visibility = View.GONE
        } else {
            emptyTxt.visibility = View.GONE
            scrollView.visibility = View.VISIBLE
        }
    }

    private fun calculateCart() {
        val percentTax = 0.02
        val delivery = 10.0
        tax = Math.round(managementCart.getTotalFee() * percentTax * 100.0) / 100.0

        val total = Math.round(managementCart.getTotalFee() + tax + delivery * 100.0) / 100
        val itemTotal = Math.round(managementCart.getTotalFee() * 100.0) / 100.0

        totalFeeTxt.text = "$$itemTotal"
        taxTxt.text = "$$tax"
        deliveryTxt.text = "$$delivery"
        totalTxt.text = "$$total"
    }

    private fun initView() {
        totalFeeTxt = findViewById(R.id.totalFeeTxt)
        taxTxt = findViewById(R.id.taxTxt)
        deliveryTxt = findViewById(R.id.deliveryTxt)
        totalTxt = findViewById(R.id.totalTxt)
        recyclerViewList = findViewById(R.id.view3)
        scrollView = findViewById(R.id.scrollView)
        backBtn = findViewById(R.id.backBtn)
        emptyTxt = findViewById(R.id.emptyTxt)
    }
}
