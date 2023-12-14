package com.example.finalproject.Data
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.UUID

@Entity(tableName = "food_items")
data class FoodItem(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    var title: String,
    var description: String,
    var picUrl: String,
    var price: Double,
    var time: Int,
    var energy: Int,
    var score: Double,
    var numberinCart: Int
):Serializable