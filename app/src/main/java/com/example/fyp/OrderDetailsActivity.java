package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.fyp.Entities.Order;

public class OrderDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        Order order=(Order) getIntent().getSerializableExtra("order");

        order.getId();
    }
}