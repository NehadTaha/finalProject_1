package com.example.finalproject.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners
import com.example.finalproject.Activity.DetailActivity
import com.example.finalproject.Data.FoodItem
import com.example.finalproject.R
import java.io.Serializable
import kotlin.collections.ArrayList

class FoodListAdapter(private val items: ArrayList<FoodItem>) : RecyclerView.Adapter<FoodListAdapter.ViewHolder>() {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_food_list, parent, false)
        context = parent.context
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.titleTxt.text = items[position].title
        holder.timeTxt.text = "${items[position].time}min"
        holder.scoreTxt.text = "${items[position].score}"
        val drawableResourceId = holder.itemView.resources.getIdentifier(items[position].picUrl, "drawable", holder.itemView.context.packageName)
        Glide.with(holder.itemView.context)
            .load(drawableResourceId)
            .transform(GranularRoundedCorners(30.0F, 30.0F, 0F, 0F))
            .into(holder.pic)
        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)

            // Assuming FoodItem implements Serializable or Parcelable
            intent.putExtra("object", items[position] as Serializable)

            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var titleTxt: TextView = itemView.findViewById(R.id.titleTxt)
        var timeTxt: TextView = itemView.findViewById(R.id.timeTxt)
        var scoreTxt: TextView = itemView.findViewById(R.id.scoreTxt)
        var pic: ImageView = itemView.findViewById(R.id.pic)
    }
}
