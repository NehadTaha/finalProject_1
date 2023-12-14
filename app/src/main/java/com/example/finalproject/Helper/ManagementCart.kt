import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.finalproject.Data.FoodItem
import com.example.finalproject.Helper.ChangeNumberItemsListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ManagementCart(private val context: Context) {
    private val tinyDB = TinyDB(context)

    fun insertFood(item: FoodItem) {
        GlobalScope.launch {
            tinyDB.saveFoodItem(item)
            showToastOnMainThread("Added to your Cart")
        }
    }

    fun getListCart(callback: (ArrayList<FoodItem>) -> Unit) {
        GlobalScope.launch {
            val cartList = tinyDB.getAllFoodItems() as ArrayList<FoodItem>
            withContext(Dispatchers.Main) {
                callback(cartList)
            }
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

    fun minusNumberFood(
        listfood: ArrayList<FoodItem>,
        position: Int,
        changeNumberItemsListener: ChangeNumberItemsListener
    ) {
        val item = listfood[position]
        if (item.numberinCart == 1) {
            GlobalScope.launch {
                tinyDB.deleteFoodItem(item)
                showToastOnMainThread("Removed from your Cart")
            }
            listfood.removeAt(position)
        } else {
            item.numberinCart = item.numberinCart - 1
            GlobalScope.launch {
                tinyDB.saveFoodItem(item)
            }
        }
        changeNumberItemsListener.changed()
    }

    fun plusNumberFood(
        listfood: ArrayList<FoodItem>,
        position: Int,
        changeNumberItemsListener: ChangeNumberItemsListener
    ) {
        val item = listfood[position]
        item.numberinCart = item.numberinCart + 1
        GlobalScope.launch {
            tinyDB.saveFoodItem(item)
        }
        changeNumberItemsListener.changed()
    }



    fun getTotalFee(callback: (Double) -> Unit) {
        getListCart { cartList ->
            var fee = 0.0
            for (item in cartList) {
                fee += item.price * item.numberinCart
            }
            callback(fee)
        }
    }

}

