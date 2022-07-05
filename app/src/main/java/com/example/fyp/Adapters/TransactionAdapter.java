package com.example.fyp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fyp.Entities.Order;
import com.example.fyp.Entities.TransactionHistories;
import com.example.fyp.R;

import java.io.Serializable;
import java.util.List;


public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.MyTransactionHolder> implements Serializable {
    List<TransactionHistories> transactionList;
    Context myContext;


    public TransactionAdapter(List<TransactionHistories>list,Context context)
    {
        this.transactionList=list;
        this.myContext=context;
    }


    @NonNull
    @Override
    public TransactionAdapter.MyTransactionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(myContext).inflate(R.layout.transaction_layout, parent, false);
        return new TransactionAdapter.MyTransactionHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionAdapter.MyTransactionHolder holder, int position) {

        holder.data=transactionList.get(position);
        holder.amount.setText(Integer.toString(holder.data.getAmountReceied()));
        holder.date.setText(holder.data.getCreatedOn().split("T")[0]);
    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }

    public class MyTransactionHolder extends RecyclerView.ViewHolder{

        TextView amount,date;
        TransactionHistories data;
        public MyTransactionHolder(@NonNull View itemView) {
            super(itemView);
            amount=itemView.findViewById(R.id.transactionAmount);
            date=itemView.findViewById(R.id.transactionDate);

        }
    }
}
