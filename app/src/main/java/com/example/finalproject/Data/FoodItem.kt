package com.example.finalproject.Data
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "food_items")
data class FoodItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var title: String,
    var description: String,
    var picUrl: String,
    var price: Double,
    var time: Int,
    var energy: Int,
    var score: Double,
    var numberinCart: Int
):Serializable