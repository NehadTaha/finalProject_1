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
    fun insertFoodItem(foodItem: FoodItem)

    @Update
    suspend fun updateFoodItem(foodItem: FoodItem)

    @Delete
    suspend fun deleteFoodItem(foodItem: FoodItem)

    @Query("DELETE FROM food_items")
    fun deleteAll()

    @Query("SELECT * FROM food_items WHERE id = :id")
    fun getFoodItemById(id: String): FoodItem?

}
