package dziejesie.app.shoppinglist.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import dziejesie.app.shoppinglist.R;
import dziejesie.app.shoppinglist.listeners.ClickListener;
import dziejesie.app.shoppinglist.models.ShoppingList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.myViewHolder>{

    private ClickListener clickListener;
    private List<ShoppingList> shoppingLists;

    public RecyclerViewAdapter(List<ShoppingList> shoppingLists, ClickListener clickListener) {
        this.shoppingLists = shoppingLists;
        this.clickListener = clickListener;
    }

    @NonNull
    @NotNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_list_item,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull myViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        if(shoppingLists != null){
            return shoppingLists.size();
        }
        return 0;
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        public myViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
        }
    }
}
