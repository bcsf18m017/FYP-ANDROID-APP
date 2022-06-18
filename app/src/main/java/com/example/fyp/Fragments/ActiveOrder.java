package com.example.fyp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.fyp.Adapters.OrderAdapter;
import com.example.fyp.Adapters.PageAdapter;
import com.example.fyp.Entities.Order;
import com.example.fyp.Entities.OrderDetails;
import com.example.fyp.Entities.Product;
import com.example.fyp.Entities.User;
import com.example.fyp.OrderDetailsActivity;
import com.example.fyp.R;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ActiveOrder extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    List<Order>orders;
    TextView textView;

    public ActiveOrder() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_active_order, container, false);


        recyclerView = view.findViewById(R.id.activeOrderRecyclerView);
        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        orders=new ArrayList<>();
        textView=view.findViewById(R.id.activeOrderText);


        loadOrders(view);


        return view;
    }
    private void loadOrders(View view)
    {
        String url="https://iqbalelectronicswebapi.azurewebsites.net/api/orders";
        StringRequest request=new StringRequest(Request.Method.GET, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response1) {
                try {
                    JSONArray arr=new JSONArray(response1);
                    for(int i=0;i<arr.length();i++)
                    {
                        JSONObject obj=arr.getJSONObject(i);
                        String personId=obj.getString("personId");
                        int billDue=obj.getInt("billDue");
                        if(personId.equals(User.user.getId())&&billDue!=0)
                        {
                            int id=obj.getInt("id");
                            int totalBill=obj.getInt("totalBill");
                            String createdOn=obj.getString("createdOn");
                            String orderDetails=obj.getString("orderDetails");
                            JSONArray array=new JSONArray(orderDetails);
                            List<OrderDetails>details=new ArrayList<>();
                            for(int j=0;j<array.length();j++)
                            {
                                JSONObject obj1=array.getJSONObject(j);
                                String productId=obj1.getString("productId");
                                int quantity=obj1.getInt("productQuantity");
                                int totalAmount= obj1.getInt("totalAmount");
                                int due=obj1.getInt("amountDue");
                                int orderId=obj1.getInt("orderId");
                                String dueDate=obj1.getString("dueDate");
                                int minimum=obj1.getInt("minimumInstallment");
                                details.add(new OrderDetails(orderId,quantity,totalAmount,due,minimum,productId,dueDate));
                            }
                            Order order=new Order(id,totalBill,billDue,createdOn,personId);
                            order.setOrderDetails(details);
                            orders.add(order);
                        }
                    }
                    if(orders.size()==0)
                    {
                        textView.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        adapter=new OrderAdapter(orders,view.getContext());
                        recyclerView.setAdapter(adapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Temporary Error While Connecting to server", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue queue= Volley.newRequestQueue(getContext());
        queue.add(request);
    }


}