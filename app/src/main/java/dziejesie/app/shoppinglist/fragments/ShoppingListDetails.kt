package dziejesie.app.shoppinglist.fragments

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dziejesie.app.shoppinglist.R
import dziejesie.app.shoppinglist.models.Product
import dziejesie.app.shoppinglist.models.ShoppingList
import dziejesie.app.shoppinglist.transporters.ListTransporter
import java.util.*
import kotlin.collections.ArrayList

class ShoppingListDetails : Fragment(){

    private val database = Firebase.firestore
    private val collection = database.collection("ShoppingLists")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_shopping_list_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        view.findViewById<Toolbar>(R.id.toolbar_list_details)
            .setupWithNavController(navController, appBarConfiguration)
        floatingButton(view)
    }

    private fun floatingButton(view: View){
        val list: ShoppingList = ListTransporter.getList()
        if(!list.archived){
            view.findViewById<FloatingActionButton>(R.id.floatingActionButtonProducts).setOnClickListener {

            }
        }

    }




}