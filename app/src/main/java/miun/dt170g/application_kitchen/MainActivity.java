package miun.dt170g.application_kitchen;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import miun.dt170g.application_kitchen.adapters.FoodAdapter;
import miun.dt170g.application_kitchen.adapters.OrdersAdapter;
import miun.dt170g.application_kitchen.adapters.StatusInterface;
import miun.dt170g.application_kitchen.entities.KitchenView;
import miun.dt170g.application_kitchen.entities.Orders;
import miun.dt170g.application_kitchen.retrofit.RetrofitClient;
import miun.dt170g.application_kitchen.retrofit.RetrofitInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements StatusInterface {

    List<KitchenView> kitchenViewMap = new ArrayList<>();
    List<KitchenView.OrderDetails> startersList = new ArrayList<>();
    List<KitchenView.OrderDetails> mainsList = new ArrayList<>();
    RetrofitInterface apiData = RetrofitClient.create();

    //RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.mRecyclerView);



        //updateOrderStatus();
        fetchKitchenViewMap(recyclerView);
    }

    private void fetchKitchenViewMap(RecyclerView recyclerView){
        // Fetching A La Carte from API with @Get method
        Call<List<KitchenView>> kitchenViewApi = apiData.getKitchenView();
        kitchenViewApi.enqueue(new  Callback<List<KitchenView>>() {
            @Override
            public void onResponse(Call<List<KitchenView>> call, Response<List<KitchenView>> response) {

                if (response.isSuccessful() && response.body() != null ) {

                    kitchenViewMap = response.body();
                    OrdersAdapter adapter = new OrdersAdapter(MainActivity.this, kitchenViewMap, MainActivity.this);
                    recyclerView.setAdapter(adapter);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
                    recyclerView.setLayoutManager(layoutManager);


                    Log.d("succ KitchenView", "succ: " + response.code());
                } else {
                    Log.d("KitchenView", "Error: " + response.code());
                    Log.d("KitchenView", "Forbidden: " + response.message());
                }
            }
            @Override
            public void onFailure(Call<List<KitchenView>> call, Throwable t) {
                Log.e("KitchenView", "Failed to fetch data", t);
            }
        });
    }

    private void updateOrderStatus(int orderId, Orders order){


        Call<Void> call = apiData.updateOrderStatus(orderId, order);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    //recyclerView.getAdapter().notifyDataSetChanged();
                    // Employee updated successfully
                    //Orders updatedEmployee = response.body();
                    // Handle the updated employee object
                } else {
                    // Handle HTTP error response
                    // For example, response.code(), response.errorBody(), etc.
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Handle failure
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onCheckedChanged(boolean isChecked, int position, String boxNum) {

        if(isChecked){
            Orders order = new Orders();
            order.setOrderId(kitchenViewMap.get(position).getOrderId());

            if(boxNum.equals("Box_1")){
                order.setStatusStart(true);
                order.setStatusMains(kitchenViewMap.get(position).getStatusMains());
            }
            else if(boxNum.equals("Box_2")) {
                order.setStatusMains(true);
                order.setStatusStart(kitchenViewMap.get(position).getStatusStart());
            }

            if(order.getStatusMains() && order.getStatusStart())
                order.setStatusOrder(true);
            else
                order.setStatusOrder(false);

            Log.d("gg", "" + position);

            updateOrderStatus(order.getOrderId(), order);
        }
    }
}