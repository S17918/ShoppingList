package dziejesie.app.shoppinglist.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dziejesie.app.shoppinglist.R
import dziejesie.app.shoppinglist.adapters.FirestoreAdapterActive
import dziejesie.app.shoppinglist.listeners.OnItemClickListener
import dziejesie.app.shoppinglist.models.ShoppingList
import dziejesie.app.shoppinglist.models.Product
import java.util.*
import kotlin.collections.ArrayList

class ShoppingListsActive : Fragment(), OnItemClickListener {

    private val database = Firebase.firestore
    private val collection = database.collection("ShoppingLists")
    private lateinit var adapter: FirestoreAdapterActive

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_shopping_lists_active, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView(view)
    }

    private fun recyclerView(view: View){
        val query = collection
            .whereNotEqualTo("date", null)
            .whereEqualTo("archived", false)
            .orderBy("date", Query.Direction.DESCENDING)
        val options: FirestoreRecyclerOptions<ShoppingList> = FirestoreRecyclerOptions.Builder<ShoppingList>()
            .setQuery(query, ShoppingList::class.java).build()
        adapter = FirestoreAdapterActive(options)
        val recyclerView: RecyclerView = view.findViewById(R.id.lists)
        recyclerView.setHasFixedSize(true)
        val linearLayout: LinearLayoutManager = LinearLayoutManager(view.context)
        linearLayout.stackFromEnd = false
        recyclerView.layoutManager = linearLayout
        recyclerView.adapter = adapter
        view.findViewById<FloatingActionButton>(R.id.floatingActionButton).setOnClickListener {
            val currentDate: Date = Calendar.getInstance().time
            val productList: ArrayList<Product> = ArrayList()
            val shoppingList: ShoppingList = ShoppingList("Shopping List", productList, currentDate, false)
            database.collection("ShoppingLists").add(shoppingList)
                .addOnSuccessListener {
                    recyclerView.scrollToPosition(0)
                    Log.d(TAG, "Successfully added!")
                }
                .addOnFailureListener {
                    Log.w(TAG, "Error" + it.printStackTrace())
                }
        }
        adapter.setOnItemClickListener(this)
    }

    override fun onItemClick(snapshot: DocumentSnapshot, position: Int) {
        collection.document(snapshot.id).update("archived", true)
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }


}