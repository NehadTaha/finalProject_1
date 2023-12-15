package com.example.finalproject.Adapter

import ManagementCart
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners
import com.example.finalproject.Data.FoodItem
import com.example.finalproject.Helper.ChangeNumberItemsListener
import com.example.finalproject.R
import java.util.ArrayList

class CartListAdapter(private val listFoodSelected: ArrayList<FoodItem>, private val context: Context,
                      private val changeNumberItemsListener: ChangeNumberItemsListener) :
    RecyclerView.Adapter<CartListAdapter.ViewHolder>() {


    private val managementCart: ManagementCart = ManagementCart(context)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_cart, parent, false)
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = listFoodSelected[position].title
        holder.feeEachItem.text = "$${listFoodSelected[position].price}"
        holder.totalEachItem.text = "$${Math.round(listFoodSelected[position].numberinCart * listFoodSelected[position].price)}"
        holder.num.text = listFoodSelected[position].numberinCart.toString()

        val drawableResourceId = holder.itemView.context.resources.getIdentifier(listFoodSelected[position].picUrl, "drawable", holder.itemView.context.packageName)

        Glide.with(holder.itemView.context)
            .load(drawableResourceId)
            .transform(GranularRoundedCorners(30F, 30F, 30F, 30F))
            .into(holder.pic)

        holder.plusItem.setOnClickListener {
            managementCart.plusNumberFood(listFoodSelected, position, changeNumberItemsListener) // Update function signature
        }

        holder.minusItem.setOnClickListener {
            managementCart.minusNumberFood(listFoodSelected, position, changeNumberItemsListener) // Update function signature
        }
    }

    override fun getItemCount(): Int {
        return listFoodSelected.size
    }
    fun updateCartItems(updatedList: ArrayList<FoodItem>) {
        listFoodSelected.clear()
        listFoodSelected.addAll(updatedList)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.titleTxt)
        val pic: ImageView = itemView.findViewById(R.id.pic2)
        val feeEachItem: TextView = itemView.findViewById(R.id.feeEachItem)
        val totalEachItem: TextView = itemView.findViewById(R.id.totalEachItem)
        val plusItem: TextView = itemView.findViewById(R.id.plusBtn)
        val minusItem: TextView = itemView.findViewById(R.id.minusBtn)
        val num: TextView = itemView.findViewById(R.id.numberitemDetaiTxt)
    }
}
