package dziejesie.app.shoppinglist.fragments

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import dziejesie.app.shoppinglist.adapters.FirestoreAdapterArchive
import dziejesie.app.shoppinglist.listeners.OnItemClickListener
import dziejesie.app.shoppinglist.models.Product
import dziejesie.app.shoppinglist.models.ShoppingList
import java.util.*
import kotlin.collections.ArrayList

class ShoppingListsArchive : Fragment(), OnItemClickListener {

    private val database = Firebase.firestore
    private val collection = database.collection("ShoppingLists")
    private lateinit var adapter: FirestoreAdapterArchive

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_shopping_lists_archive, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView(view)
    }

    private fun recyclerView(view: View){
        val query = collection
            .whereNotEqualTo("date", null)
            .whereEqualTo("archived", true)
            .orderBy("date", Query.Direction.DESCENDING)
        val options: FirestoreRecyclerOptions<ShoppingList> = FirestoreRecyclerOptions.Builder<ShoppingList>()
            .setQuery(query, ShoppingList::class.java).build()
        adapter = FirestoreAdapterArchive(options)
        val recyclerView: RecyclerView = view.findViewById(R.id.lists_archive)
        recyclerView.setHasFixedSize(true)
        val linearLayout: LinearLayoutManager = LinearLayoutManager(view.context)
        linearLayout.stackFromEnd = false
        recyclerView.layoutManager = linearLayout
        recyclerView.adapter = adapter
        adapter.setOnItemClickListener(this)
    }

    override fun onItemClick(snapshot: DocumentSnapshot, position: Int) {
        collection.document(snapshot.id).delete()
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