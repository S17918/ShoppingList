package dziejesie.app.shoppinglist.adapters;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import dziejesie.app.shoppinglist.R;
import dziejesie.app.shoppinglist.listeners.ClickListener;
import dziejesie.app.shoppinglist.models.Product;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.myViewHolder>{

    private ClickListener clickListener;
    private List<Product> productList;

    public ProductAdapter(List<Product> productList, ClickListener clickListener) {
        this.productList = productList;
        this.clickListener = clickListener;
    }

    public ProductAdapter() {
    }

    @NonNull
    @NotNull
    @Override
    public ProductAdapter.myViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list_item,parent,false);
        return new myViewHolder(view);
    }

    public void update(){
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ProductAdapter.myViewHolder holder, int position) {
        Product product = productList.get(position);

        holder.productName.setText(product.getName());
        holder.productQuantity.setText("x"+product.getQuantity());
    }

    @Override
    public int getItemCount() {
        if(productList != null){
            return productList.size();
        }
        return 0;
    }

    public class myViewHolder extends RecyclerView.ViewHolder{

        public TextView productName, productQuantity;

        public myViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            productName = itemView.findViewById(R.id.product_name);
            productQuantity = itemView.findViewById(R.id.product_quantity);
        }
    }
}
