package com.example.fyp.Api;

import android.app.Person;

import com.example.fyp.Entities.Login;
import com.example.fyp.Entities.Order;
import com.example.fyp.Entities.OrderDetails;
import com.example.fyp.Entities.OrderDetailsRequest;
import com.example.fyp.Entities.OrderRequest;
import com.example.fyp.Entities.PersonRequest;
import com.example.fyp.Entities.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface ApiInterface {

    @POST("/api/authentication/login")
    Call<User> postLogin(@Body Login login);


    @POST("/api/orders")
    Call<Order>postOrder(@Body OrderRequest body);

    @POST("/api/orderDetails")
    Call<OrderDetails>postOrderDetail(@Body OrderDetailsRequest orderDetailsRequest);

    @PUT("/api/Person/ChangePassword/{id}/{current}/{newPassword}")
    Call<String> changePassword(@Path("id") String id, @Path("current") String current,@Path("newPassword") String newPassword);


    @PUT("/api/person/{id}/{name}/{address}")
    Call<User>changeProfile(@Path("id")String id,@Path("name") String name,@Path("address")String address);

}
