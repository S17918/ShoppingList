package dziejesie.app.shoppinglist.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dziejesie.app.shoppinglist.R
import dziejesie.app.shoppinglist.adapters.ProductAdapter
import dziejesie.app.shoppinglist.listeners.ClickListener
import dziejesie.app.shoppinglist.models.Product
import dziejesie.app.shoppinglist.models.ShoppingList
import dziejesie.app.shoppinglist.transporters.ArchiveListTransporter
import dziejesie.app.shoppinglist.transporters.ListTransporter

class ShoppingListDetailsArchive : Fragment(), ClickListener {

    private val database = Firebase.firestore
    private val collection = database.collection("ShoppingLists")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_shopping_list_details_archive, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        view.findViewById<Toolbar>(R.id.toolbar_list_details_archive)
            .setupWithNavController(navController, appBarConfiguration)
        recyclerView(view)
    }

    private fun recyclerView(view: View){
        val recyclerView = view.findViewById<RecyclerView>(R.id.products_archive_recycler)
        val layoutManager = LinearLayoutManager(view.context)
        val adapter: ProductAdapter = ProductAdapter(ArchiveListTransporter.getList().productList, this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }

    override fun onClick(pos: Int) {
        TODO("Not yet implemented")
    }
}