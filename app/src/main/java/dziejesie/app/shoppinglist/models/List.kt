package dziejesie.app.shoppinglist.models

import java.util.*
import kotlin.collections.ArrayList

class List(
    val id: Int,
    val productList: ArrayList<Product>,
    val date: Date
) {
}