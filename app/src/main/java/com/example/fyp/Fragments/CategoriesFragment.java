package com.example.fyp.Fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fyp.Adapters.ProductAdapter;
import com.example.fyp.Entities.Product;
import com.example.fyp.ProductDetails;
import com.example.fyp.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class CategoriesFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private String category;
    public CategoriesFragment(String category)
    {
        this.category=category;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_categories, container, false);

        recyclerView=view.findViewById(R.id.productDisplayView);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        List<Product>list=new ArrayList<>();
        for (Product p:Product.productList) {
            if(p.getCategory().equals(category))
            {
                list.add(p);
            }

        }
        adapter=new ProductAdapter(view.getContext(), list, new ProductAdapter.ItemClickListener() {
            @Override
            public void onItemClick(Product product) {
                Intent intent =new Intent(getContext(), ProductDetails.class);
                intent.putExtra("Details", product);
                intent.putExtra("Caller","Home");
                startActivity(intent);
                ((Activity)getContext()).finish();
            }
        });
        recyclerView.setAdapter(adapter);
        return view;
    }
}