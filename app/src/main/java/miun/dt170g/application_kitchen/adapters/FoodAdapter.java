package miun.dt170g.application_kitchen.adapters;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import miun.dt170g.application_kitchen.R;
import miun.dt170g.application_kitchen.entities.KitchenView;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.MyViewHolder> {

    Context context;
    List<KitchenView.OrderDetails> orderDetailsList;
    String color;

    public FoodAdapter(Context context, List<KitchenView.OrderDetails> orderDetailsList, String color){
        this.context = context;
        this.orderDetailsList = orderDetailsList;
        this.color = color;
    }

    @NonNull
    @Override
    public FoodAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.food_template, parent, false);
        return new FoodAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodAdapter.MyViewHolder holder, int position) {

        holder.foodName.setText(orderDetailsList.get(position).getName());
        holder.foodAmount.setText(String.valueOf(orderDetailsList.get(position).getAmount()));
        //KitchenView.OrderDetails orderDetails = orderDetailsList.get(position);

        if (color.equals("green")){
            holder.foodCard.setCardBackgroundColor(ContextCompat.getColor(context.getApplicationContext(), R.color.green));
        }

    }

    @Override
    public int getItemCount() {
        return orderDetailsList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView foodName, foodAmount;
        CardView foodCard;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            foodName = itemView.findViewById(R.id.foodNameTV);
            foodAmount = itemView.findViewById(R.id.foodAmountTV);
            foodCard = itemView.findViewById(R.id.foodCard);
        }
    }
}

