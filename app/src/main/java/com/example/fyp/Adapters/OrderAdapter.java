package com.example.fyp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fyp.Entities.Order;
import com.example.fyp.OrderDetailsActivity;
import com.example.fyp.R;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyOrderHolder> implements Serializable {

    List<Order> orderList;
    Context myContext;

    public OrderAdapter(List<Order> orderList, Context myContext) {
        this.orderList = orderList;
        this.myContext = myContext;
    }

    @NonNull
    @Override
    public MyOrderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(myContext).inflate(R.layout.order_layout, parent, false);
        return new OrderAdapter.MyOrderHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyOrderHolder holder, int position) {
        holder.data=orderList.get(position);
        String date=holder.data.getDueDate().split("T")[0];
        holder.date.setText(date);
        holder.total.setText(Integer.toString(holder.data.getTotalBill()));
        holder.due.setText(Integer.toString(holder.data.getBillDue()));

        holder.details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(myContext, OrderDetailsActivity.class);
                intent.putExtra("order",holder.data);
                myContext.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }


    public class MyOrderHolder extends RecyclerView.ViewHolder {
        TextView date,total,due;
        Button details;
        Order data;

        public MyOrderHolder(@NonNull View itemView) {
            super(itemView);
            date=itemView.findViewById(R.id.orderDate);
            total=itemView.findViewById(R.id.totalBill);
            due=itemView.findViewById(R.id.billLeft);
            details=itemView.findViewById(R.id.orderDetailsButton);
        }


    }
}
