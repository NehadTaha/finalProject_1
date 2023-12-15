package com.example.finalproject

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.finalproject.Data.AppDatabase
import com.example.finalproject.Data.FoodItem
import com.example.finalproject.Data.FoodItemDao
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class FoodItemDaoTest {

    private lateinit var foodItemDao: FoodItemDao
    private lateinit var database: AppDatabase
    private val sampleFoodItem = FoodItem(
        title = "Pizzzaaa",
        description = "Cheeeeesy",
        picUrl = "sample_url",
        price = 10.0,
        time = 20,
        energy = 30,
        score = 4.5,
        numberinCart = 5
    )

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        foodItemDao = database.foodItemDao()
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        database.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertFoodItemAndGetById() = runBlocking {
        // Insert sample data
        foodItemDao.insertFoodItem(sampleFoodItem)

        // Get the inserted data by ID
        val retrievedItem = foodItemDao.getFoodItemById(sampleFoodItem.id)

        // Assert that the retrieved data matches the inserted data
        assertEquals(sampleFoodItem, retrievedItem)
    }

    @Test
    @Throws(Exception::class)
    fun updateFoodItem() = runBlocking {
        // Insert sample data
        foodItemDao.insertFoodItem(sampleFoodItem)

        // Modify some property of the sample data
        val updatedItem = sampleFoodItem.copy(title = "Updated Pizzzzaaaaaaa")

        // Update the item in the database
        foodItemDao.updateFoodItem(updatedItem)

        // Get the updated item by ID
        val retrievedItem = foodItemDao.getFoodItemById(updatedItem.id)

        // Assert that the retrieved data matches the updated data
        assertEquals(updatedItem, retrievedItem)
    }

    @Test
    @Throws(Exception::class)
    fun deleteFoodItem() = runBlocking {
        // Insert sample data
        foodItemDao.insertFoodItem(sampleFoodItem)

        // Delete the inserted item
        foodItemDao.deleteFoodItem(sampleFoodItem)

        // Attempt to retrieve the deleted item by ID
        val retrievedItem = foodItemDao.getFoodItemById(sampleFoodItem.id)

        // Assert that the retrieved item is null, indicating deletion
        assertEquals(null, retrievedItem)
    }

    @Test
    @Throws(Exception::class)
    fun getAllFoodItems() = runBlocking {
        // Insert multiple sample data
        val sampleFoodItem2 = FoodItem(
            title = "Another Food",
            description = "Another Description",
            picUrl = "another_url",
            price = 15.0,
            time = 25,
            energy = 35,
            score = 4.0,
            numberinCart = 8
        )
        foodItemDao.insertFoodItem(sampleFoodItem)
        foodItemDao.insertFoodItem(sampleFoodItem2)

        // Get all items from the database
        val allFoodItems = foodItemDao.getAllFoodItems()

        // Assert that the list contains both inserted items
        assertEquals(2, allFoodItems.size)
        assertTrue(allFoodItems.contains(sampleFoodItem))
        assertTrue(allFoodItems.contains(sampleFoodItem2))
    }

    @Test
    @Throws(Exception::class)
    fun deleteAllFoodItems() = runBlocking {
        // Insert sample data
        foodItemDao.insertFoodItem(sampleFoodItem)

        // Delete all items from the database
        foodItemDao.deleteAll()

        // Get all items from the database after deletion
        val allFoodItems = foodItemDao.getAllFoodItems()

        // Assert that the list is empty after deletion
        assertTrue(allFoodItems.isEmpty())
    }
}