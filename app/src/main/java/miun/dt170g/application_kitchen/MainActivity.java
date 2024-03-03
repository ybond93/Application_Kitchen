package miun.dt170g.application_kitchen;

import android.os.Bundle;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import miun.dt170g.application_kitchen.entities.AlacarteMenuItem;
import miun.dt170g.application_kitchen.entities.Table;
import miun.dt170g.application_kitchen.retrofit.RetrofitClient;
import miun.dt170g.application_kitchen.retrofit.RetrofitInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RetrofitInterface apiData = RetrofitClient.create();

        // Fetching A La Carte from API with @Get method
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

        // Fetching tables from API with @Get method
        Call<ArrayList<Table>> tables = apiData.getTable();
        tables.enqueue(new Callback<ArrayList<Table>>() {
            @Override
            public void onResponse(Call<ArrayList<Table>> call, Response<ArrayList<Table>> response) {

                if (response.isSuccessful() && response.body() != null ) {

                    ArrayList<Table> alacarteMenuItemList = response.body();
                    Log.e("succ", "succ: " + response.code());

                } else {
                    Log.e("API Error", "Error: " + response.code());
                    Log.e("API Error", "Forbidden: " + response.message());
                }
            }
            @Override
            public void onFailure(Call<ArrayList<Table>> call, Throwable t) {
                Log.e("API Error", "Failed to fetch data", t);
            }
        });

        // Update table status with @Put method
        List<Integer> tt =  new ArrayList<>();
        Table temp = new Table(5, "In Progress",tt);
        Call<Table> call = apiData.updateTableStatus(5, temp);
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
        });
    }
}