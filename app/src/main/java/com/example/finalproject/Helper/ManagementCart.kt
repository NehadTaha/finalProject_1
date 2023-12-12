import android.content.Context
import android.widget.Toast
import com.example.finalproject.Domain.FoodDomain
import com.example.finalproject.Helper.TinyDB

class ManagementCart(private val context: Context) {
    private val tinyDB = TinyDB(context) // Initialize TinyDB here

    fun insertFood(item: FoodDomain) {
        val listFood = getListCart()
        var existAlready = false
        var n = 0
        for (y in listFood.indices) {
            if (listFood[y].title == item.title) {
                existAlready = true
                n = y
                break
            }
        }
        if (existAlready) {
            listFood[n].numberinCart = item.numberinCart
        } else {
            listFood.add(item)
        }
        tinyDB.putListObject("CartList", listFood)
        Toast.makeText(context,"Added to your Cart",Toast.LENGTH_SHORT).show()
    }

    fun getListCart(): ArrayList<FoodDomain> {
        return tinyDB.getListObject("CartList") ?: ArrayList()
    }
}
