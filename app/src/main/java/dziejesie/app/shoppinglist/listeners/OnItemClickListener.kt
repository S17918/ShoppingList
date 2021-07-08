package dziejesie.app.shoppinglist.listeners

import com.google.firebase.firestore.DocumentSnapshot

interface OnItemClickListener {
    fun onItemClick(snapshot: DocumentSnapshot, position: Int)
}