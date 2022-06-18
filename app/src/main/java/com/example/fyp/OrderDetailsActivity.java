package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.fyp.Adapters.OrderAdapter;
import com.example.fyp.Adapters.OrderDetailsAdapter;
import com.example.fyp.Entities.Order;
import com.example.fyp.Entities.OrderDetails;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        recyclerView = findViewById(R.id.activeOrderDetailsRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(OrderDetailsActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        Order order=(Order) getIntent().getSerializableExtra("order");
        List<OrderDetails>details=order.getOrderDetails();
        adapter=new OrderDetailsAdapter(details,OrderDetailsActivity.this);
        recyclerView.setAdapter(adapter);
    }
}