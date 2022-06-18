package com.example.fyp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fyp.Entities.Order;
import com.example.fyp.Entities.OrderDetails;
import com.example.fyp.Entities.Product;
import com.example.fyp.OrderDetailsActivity;
import com.example.fyp.R;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;


public class OrderDetailsAdapter extends RecyclerView.Adapter<OrderDetailsAdapter.MyOrderDetailsHolder> implements Serializable {

    List<OrderDetails> orderList;
    Context myContext;
    public OrderDetailsAdapter(List<OrderDetails> orderList, Context myContext)
    {
        this.orderList=orderList;
        this.myContext=myContext;
    }


    @NonNull
    @Override
    public OrderDetailsAdapter.MyOrderDetailsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(myContext).inflate(R.layout.order_detail_layout, parent, false);
        return new OrderDetailsAdapter.MyOrderDetailsHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailsAdapter.MyOrderDetailsHolder holder, int position) {

        holder.data=orderList.get(position);
        String date=holder.data.getDueDate().split("T")[0];
        holder.date.setText(date);
        holder.total.setText(Integer.toString(holder.data.getTotalAmount()));
        holder.due.setText(Integer.toString(holder.data.getAmountDue()));
        holder.installment.setText(Integer.toString(holder.data.getMinimumInstallment()));
        Product p=Product.getProductByID(holder.data.getProductID());
        Picasso.get().load(p.getImage_id()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class MyOrderDetailsHolder extends RecyclerView.ViewHolder{

        TextView date,total,due,installment;
        OrderDetails data;
        ImageView imageView;
        public MyOrderDetailsHolder(@NonNull View itemView) {
            super(itemView);
            date=itemView.findViewById(R.id.orderDetailDate);
            due=itemView.findViewById(R.id.orderDetailbillLeft);
            total=itemView.findViewById(R.id.orderDetailTotal);
            installment=itemView.findViewById(R.id.orderDetailInstallment);
            imageView=itemView.findViewById(R.id.orderDetailImage);

        }
    }
}
