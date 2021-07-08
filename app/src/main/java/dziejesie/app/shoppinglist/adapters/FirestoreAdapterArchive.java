package dziejesie.app.shoppinglist.adapters;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;

import dziejesie.app.shoppinglist.R;
import dziejesie.app.shoppinglist.listeners.OnItemClickListener;
import dziejesie.app.shoppinglist.models.ShoppingList;

public class FirestoreAdapterArchive extends FirestoreRecyclerAdapter<ShoppingList, FirestoreAdapterArchive.DataHolder> {

    private OnItemClickListener listener;

    public FirestoreAdapterArchive(@NonNull @NotNull FirestoreRecyclerOptions<ShoppingList> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull DataHolder holder, int position, @NonNull @NotNull ShoppingList model) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd");
        String date = simpleDateFormat.format(model.getDate());

        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("kk:mm");
        String time = simpleDateFormat2.format(model.getDate());

        holder.listName.setText(model.getName());
        holder.listDate.setText(date);
        holder.listTime.setText(time);
    }

    @NonNull
    @NotNull
    @Override
    public DataHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_list_item,parent,false);
        return new DataHolder(view);
    }

    class DataHolder extends RecyclerView.ViewHolder{

        public TextView listName, listDate, listTime;

        public DataHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            listName = itemView.findViewById(R.id.list_name);
            listDate = itemView.findViewById(R.id.list_date);
            listTime = itemView.findViewById(R.id.list_time);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    NavController controller = Navigation.findNavController(view);
                    controller.navigate(R.id.shopping_list_details);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext(), R.style.DialogStyle);
                    alert.setTitle("Alert !");
                    alert.setMessage("Do you want to delete this Shopping List?");
                    alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            int position = getAdapterPosition();
                            if(position != RecyclerView.NO_POSITION && listener != null){
                                listener.onItemClick(getSnapshots().getSnapshot(position), position);
                            }
                        }
                    });
                    alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    alert.show();
                    return true;
                }
            });
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
