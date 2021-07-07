package dziejesie.app.shoppinglist.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import dziejesie.app.shoppinglist.R
import dziejesie.app.shoppinglist.adapters.SectionsPageAdapter

class ShoppingLists : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewRoot = inflater.inflate(R.layout.fragment_shopping_lists, container, false)
        val tabLayout: TabLayout = viewRoot.findViewById(R.id.tabs)
        val viewPager: ViewPager = viewRoot.findViewById(R.id.container)
        init(viewPager)
        tabLayout.setupWithViewPager(viewPager)

        return viewRoot
    }

    private fun init(viewPager: ViewPager) {
        val adapter = SectionsPageAdapter(childFragmentManager)
        adapter.addFragment(ShoppingListsActive(), resources.getString(R.string.shopping_lists_active))
        adapter.addFragment(ShoppingListsArchive(), resources.getString(R.string.shopping_lists_archive))
        viewPager.adapter = adapter
    }

}