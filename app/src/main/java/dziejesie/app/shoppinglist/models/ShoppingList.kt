package dziejesie.app.shoppinglist.models

import java.util.*
import kotlin.collections.ArrayList

class ShoppingList(
    val name: String,
    val productList: ArrayList<Product>,
    val date: Date
) {
}