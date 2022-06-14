package com.example.fyp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fyp.Adapters.HistoryAdapter;
import com.example.fyp.Adapters.OrderAdapter;
import com.example.fyp.Entities.Order;
import com.example.fyp.Entities.OrderDetails;
import com.example.fyp.Entities.User;
import com.example.fyp.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderHistory extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public OrderHistory() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_order_history, container, false);

        recyclerView = view.findViewById(R.id.HistoryOrderRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        List<Order> list=new ArrayList<>();


        Date date1 = new Date();


        Order o=new Order(1,200,100,date1, User.user.getId());
        Order o1=new Order(2,200,100,date1, User.user.getId());
        Order o2=new Order(3,200,100,date1, User.user.getId());
        Order o3=new Order(4,200,100,date1, User.user.getId());

        List<OrderDetails>details=new ArrayList<>();
        com.example.fyp.Entities.OrderDetails d=new com.example.fyp.Entities.OrderDetails(1,10,20000,10000,1000,"1",date1);
        com.example.fyp.Entities.OrderDetails d1=new com.example.fyp.Entities.OrderDetails(1,10,20000,10000,1000,"1",date1);
        com.example.fyp.Entities.OrderDetails d2=new com.example.fyp.Entities.OrderDetails(1,10,20000,10000,1000,"1",date1);
        com.example.fyp.Entities.OrderDetails d3=new com.example.fyp.Entities.OrderDetails(1,10,20000,10000,1000,"1",date1);

        details.add(d);
        details.add(d1);
        details.add(d2);
        details.add(d3);

        o.setOrderDetails(details);

        list.add(o);
        list.add(o1);
        list.add(o2);
        list.add(o3);

        adapter=new HistoryAdapter(list,view.getContext());
        recyclerView.setAdapter(adapter);

        return view;
    }
}