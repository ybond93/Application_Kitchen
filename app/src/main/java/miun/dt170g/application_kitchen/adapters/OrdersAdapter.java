package miun.dt170g.application_kitchen.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import miun.dt170g.application_kitchen.R;
import miun.dt170g.application_kitchen.entities.KitchenView;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.MyViewHolder> {

    Context context;
    List<KitchenView> kitchenViewMap;

    StatusInterface statusInterface;

    public OrdersAdapter(Context context, List<KitchenView> kitchenViewMap, StatusInterface statusInterface){
        this.context = context;
        this.kitchenViewMap = kitchenViewMap;
        this.statusInterface = statusInterface;
    }
    @NonNull
    @Override
    public OrdersAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate the layout (giving a look to our rows)
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.order_template, parent, false);
        return new OrdersAdapter.MyViewHolder(view, statusInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        //assigning values to the views we created in the recycler_view_row layout file
        //based on the position of the recycler view

        holder.tableNum.setText("Table Number: " + kitchenViewMap.get(position).getTableNum());
        //holder.starters.setText(orderModels.get(position).getMains());
        holder.positionId = position;


        List<KitchenView.OrderDetails> startersList = new ArrayList<>();
        List<KitchenView.OrderDetails> mainsList = new ArrayList<>();

        for (KitchenView.OrderDetails order : kitchenViewMap.get(position).getOrderDetails()){

            if(order.getCategory().equals("Starter")){
                startersList.add(order);
            }else {
                mainsList.add(order);
            }
        }
        //check there is items in the both starters and mains arraies.
        String color = "noColor";
        if (kitchenViewMap.get(position).getStatusStart()){
            color = "green";
        }


        FoodAdapter startersAdapter = new FoodAdapter(context.getApplicationContext(), startersList, color);
        holder.starters.setAdapter(startersAdapter);
        holder.starters.setLayoutManager(new LinearLayoutManager(context.getApplicationContext()));

        if (kitchenViewMap.get(position).getStatusMains()){
            color = "green";
        }

        FoodAdapter mainsAdapter = new FoodAdapter(context.getApplicationContext(), mainsList, color);
        holder.mains.setAdapter(mainsAdapter);
        holder.mains.setLayoutManager(new LinearLayoutManager(context.getApplicationContext()));




    }

    @Override
    public int getItemCount() {
        // number of items we want to display
        return kitchenViewMap.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        // grabbing all views from our recycler_view_row layout file
        // like onCreate method ish

        TextView tableNum;
        RecyclerView starters, mains;
        CheckBox startersCB, mainsCB;
        int positionId;
        public MyViewHolder(@NonNull View itemView, StatusInterface statusInterface) {
            super(itemView);
            tableNum = itemView.findViewById(R.id.tableIdTV);
            starters = itemView.findViewById(R.id.startersRV);
            mains = itemView.findViewById(R.id.mainsRV);
            startersCB = itemView.findViewById((R.id.startersStatusCB));
            mainsCB = itemView.findViewById(R.id.mainsStatusCB);

            startersCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    statusInterface.onCheckedChanged(isChecked, positionId, "Box_1");
                }
            });

            mainsCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    statusInterface.onCheckedChanged(isChecked, positionId, "Box_2");
                }
            });

        }
    }
}
