package dziejesie.app.shoppinglist.models

import java.util.*
import kotlin.collections.ArrayList

data class ShoppingList(
    val name: String = "",
    val productList: ArrayList<Product> = ArrayList(),
    val date: Date = Calendar.getInstance().time,
    val archived: Boolean = false
) {
}