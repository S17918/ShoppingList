package dziejesie.app.shoppinglist.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dziejesie.app.shoppinglist.R

class ShoppingListsActive : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_shopping_lists_active, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val addButton: FloatingActionButton = view.findViewById(R.id.floatingActionButton)
        addButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.add_shopping_list)
        }
    }

}