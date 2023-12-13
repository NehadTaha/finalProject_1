package com.example.finalproject.Data
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface FoodItemDao {
    @Query("SELECT* FROM food_items")
    fun getAllFoodItems(): List<FoodItem>

    @Insert
    fun insertFoodItem(foodDomain: FoodItem)

    @Update
    suspend fun updateFoodItem(foodDomain: FoodItem)

    @Delete
    suspend fun deleteFoodItem(foodDomain: FoodItem)
}
