package miun.dt170g.application_kitchen;

import android.os.Bundle;
import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import miun.dt170g.application_kitchen.Adapters.TableAdapter;
import miun.dt170g.application_kitchen.entities.AlacarteMenuItem;
import miun.dt170g.application_kitchen.retrofit.RetrofitClient;
import miun.dt170g.application_kitchen.retrofit.RetrofitInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TableAdapter adapter;
    private List<String> tableNumbers = Arrays.asList("Table 1", "Table 2", "Table 3", "Table 4", "Table 5", "Table 6", "Table 7", "Table 8", "Table 9", "Table 10"); // Example table numbers

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RetrofitInterface apiData = RetrofitClient.create();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter = new TableAdapter(tableNumbers);
        recyclerView.setAdapter(adapter);

        Call<ArrayList<AlacarteMenuItem>> alacarteMenuItemApi = apiData.getAlacarteMenuItem();
        alacarteMenuItemApi.enqueue(new Callback<ArrayList<AlacarteMenuItem>>() {
            @Override
            public void onResponse(Call<ArrayList<AlacarteMenuItem>> call, Response<ArrayList<AlacarteMenuItem>> response) {

                if (response.isSuccessful() && response.body() != null ) {

                    ArrayList<AlacarteMenuItem> alacarteMenuItemList = response.body();
                    Log.e("succ", "succ: " + response.code());

                } else {
                    Log.e("API Error", "Error: " + response.code());
                    Log.e("API Error", "Forbidden: " + response.message());
                }
            }
            @Override
            public void onFailure(Call<ArrayList<AlacarteMenuItem>> call, Throwable t) {

                Log.e("API Error", "Failed to fetch data", t);
            }
        });



        //List<Integer> tt =  new ArrayList<>();
        //Table temp = new Table(5, "In Progress",tt);
        /*Call<Table> call = apiData.updateTableStatus(5, temp);
        call.enqueue(new Callback<Table>() {
            @Override
            public void onResponse(Call<Table> call, Response<Table> response) {
                if (response.isSuccessful()) {
                    Table updatedTable = response.body();
                } else {
                }
            }
            @Override
            public void onFailure(Call<Table> call, Throwable t) {
                t.printStackTrace();
            }
        });*/
    }
}