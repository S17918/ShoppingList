package dziejesie.app.shoppinglist.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dziejesie.app.shoppinglist.R
import dziejesie.app.shoppinglist.models.ShoppingList
import dziejesie.app.shoppinglist.models.Product
import java.util.*
import kotlin.collections.ArrayList

class ShoppingListsActive : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_shopping_lists_active, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val database = Firebase.firestore
        view.findViewById<FloatingActionButton>(R.id.floatingActionButton).setOnClickListener {
            val currentDate: Date = Calendar.getInstance().time
            val productList: ArrayList<Product> = ArrayList()
            val shoppingList: ShoppingList = ShoppingList("Shopping List", productList, currentDate)
            database.collection("ShoppingLists").add(shoppingList)
                .addOnSuccessListener {
                    Log.d(TAG, "Successfully added!");
                }
                .addOnFailureListener {
                    Log.w(TAG, "Error" + it.printStackTrace());
                }
        }
    }

}