import android.content.Context
import com.example.finalproject.Data.AppDatabase
import com.example.finalproject.Data.FoodItem

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TinyDB(private val appContext: Context) {

    private val appDatabase: AppDatabase = AppDatabase.getInstance(appContext)

    suspend fun saveFoodItem(foodItem: FoodItem) {
        withContext(Dispatchers.IO) {
            appDatabase.foodItemDao().insertFoodItem(foodItem)
        }
    }

    suspend fun getAllFoodItems(): List<FoodItem> {
        return withContext(Dispatchers.IO) {
            appDatabase.foodItemDao().getAllFoodItems()
        }
    }

    suspend fun deleteFoodItem(foodItem: FoodItem) {
        withContext(Dispatchers.IO) {
            appDatabase.foodItemDao().deleteFoodItem(foodItem)
        }
    }

    // Other methods for database operations can be added here
}
