package dziejesie.app.shoppinglist.listeners

interface FirebaseListener {
    fun onCallBack(list: List<dziejesie.app.shoppinglist.models.ShoppingList>)
}