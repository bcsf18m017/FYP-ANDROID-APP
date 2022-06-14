package com.example.fyp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fyp.Entities.Order;
import com.example.fyp.OrderDetailsActivity;
import com.example.fyp.R;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyHistoryHolder> implements Serializable {
    List<Order> orderList;
    Context myContext;

    public HistoryAdapter(List<Order> orderList, Context myContext) {
        this.orderList = orderList;
        this.myContext = myContext;
    }

    @NonNull
    @Override
    public HistoryAdapter.MyHistoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(myContext).inflate(R.layout.history_layout, parent, false);
        return new HistoryAdapter.MyHistoryHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.MyHistoryHolder holder, int position) {
        holder.data=orderList.get(position);
        SimpleDateFormat dateFormat= new SimpleDateFormat("dd/MMM/yyyy");
        String dateOnly = dateFormat.format(holder.data.getDueDate());
        holder.date.setText(dateOnly);
        holder.total.setText(Integer.toString(holder.data.getTotalBill()));

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


    public class MyHistoryHolder extends RecyclerView.ViewHolder {
        TextView date,total;
        Button details;
        Order data;

        public MyHistoryHolder(@NonNull View itemView) {
            super(itemView);
            date=itemView.findViewById(R.id.orderDate);
            total=itemView.findViewById(R.id.totalBill);
            details=itemView.findViewById(R.id.orderDetailsButton);
        }


    }
}
