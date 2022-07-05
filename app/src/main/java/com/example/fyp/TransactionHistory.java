package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.fyp.Adapters.OrderAdapter;
import com.example.fyp.Adapters.OrderDetailsAdapter;
import com.example.fyp.Adapters.TransactionAdapter;
import com.example.fyp.Entities.Order;
import com.example.fyp.Entities.OrderDetails;
import com.example.fyp.Entities.TransactionHistories;
import com.example.fyp.Entities.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TransactionHistory extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    List<TransactionHistories>list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);

        recyclerView = findViewById(R.id.transactionsRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(TransactionHistory.this);
        recyclerView.setLayoutManager(layoutManager);

        loadTransactions();

    }

    private  void loadTransactions()
    {
        String url="https://iqbalelectronicswebapi.azurewebsites.net/api/transactionhistories";
        StringRequest request=new StringRequest(Request.Method.GET, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response1) {
                try {
                    JSONArray arr=new JSONArray(response1);
                    for(int i=0;i<arr.length();i++) {
                        JSONObject obj = arr.getJSONObject(i);
                        String personId = obj.getString("personID");
                        if (personId.equals(User.user.getId())) {
                            int amount = obj.getInt("amountReceived");
                            String id = obj.getString("transactionHistoryId");
                            String createdOn = obj.getString("createdOn");
                            String orderDetails = obj.getString("orderDetailId");
                            list.add(new TransactionHistories(id,orderDetails,amount,personId,createdOn));
                        }
                    }
                    adapter = new TransactionAdapter(list, TransactionHistory.this);
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TransactionHistory.this, "Temporary Error While Connecting to server", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue queue= Volley.newRequestQueue(TransactionHistory.this);
        queue.add(request);
    }
}