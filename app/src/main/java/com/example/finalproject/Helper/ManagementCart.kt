import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.finalproject.Data.FoodItem
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ManagementCart(private val context: Context) {
    private val tinyDB = TinyDB(context)

    fun insertFood(item: FoodItem) {
        GlobalScope.launch {
            tinyDB.saveFoodItem(item)
            showToastOnMainThread("Added to your Cart")
        }
    }

    fun getListCart(callback: (List<FoodItem>) -> Unit) {
        GlobalScope.launch {
            val cartList = tinyDB.getAllFoodItems()
            callback(cartList)
        }
    }
    private fun showToastOnMainThread(message: String) {
        // Switch to the main thread to display the Toast
        (context as? AppCompatActivity)?.runOnUiThread {
            showToast(message)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}
