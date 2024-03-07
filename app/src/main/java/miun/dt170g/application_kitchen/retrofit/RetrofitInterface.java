package miun.dt170g.application_kitchen.retrofit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import miun.dt170g.application_kitchen.entities.AlacarteMenuItem;
import miun.dt170g.application_kitchen.entities.KitchenView;
import miun.dt170g.application_kitchen.entities.Orders;
import miun.dt170g.application_kitchen.entities.Table;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RetrofitInterface {

    @GET("alacartemenuitem")
    Call<ArrayList<AlacarteMenuItem>> getAlacarteMenuItem();

    @GET("tables")
    Call<ArrayList<Table>> getTable();

    @PUT("orders/{orderId}")
    Call<Void> updateOrderStatus(@Path("orderId") int orderId, @Body Orders orders);

    @GET("KitchenOrder")
    Call<List<KitchenView>> getKitchenView();

    /*@GET("employees")
    Call<ArrayList<Employee>> getEmployee();

    @GET("orders")
    Call<ArrayList<Order>> getOrder();

    @GET("tables")
    Call<ArrayList<Table>> getTable();

    @POST("orders")
    Call<Void> sendOrder(@Body Order order);

    @PUT("employees/{empId}")
    Call<Employee> updateEmployee(@Path("empId") int employeeId, @Body Employee employee);*/
}