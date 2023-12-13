import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.finalproject.Data.FoodItem
import com.example.finalproject.Helper.ChangeNumberItemsListener
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

    fun minusNumberFood(listfood: ArrayList<FoodItem>, position: Int, changeNumberItemsListener: ChangeNumberItemsListener) {
        if (listfood[position].numberinCart == 1) {
            listfood.removeAt(position)
        } else {
            listfood[position].numberinCart = listfood[position].numberinCart - 1
        }
        tinyDB.putListObject("CartList", listfood)
        changeNumberItemsListener.changed()
    }

    fun plusNumberFood(listfood: ArrayList<FoodItem>, position: Int, changeNumberItemsListener: ChangeNumberItemsListener) {
        listfood[position].numberinCart = listfood[position].numberinCart + 1
        tinyDB.putListObject("CartList", listfood)
        changeNumberItemsListener.changed()
    }

    fun getTotalFee(): Double {
        val listfood2 = getListCart()
        var fee = 0.0
        for (item in listfood2) {
            fee += item.price * item.numberinCart
        }
        return fee
    }


}
