package com.example.fyp.Fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fyp.Adapters.ProductAdapter;
import com.example.fyp.Entities.Product;
import com.example.fyp.ProductDetails;
import com.example.fyp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchFragment extends Fragment {


    EditText searchbar;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    List<Product> productList=new ArrayList<>();
    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_search, container, false);
        searchbar=view.findViewById(R.id.searcBar);


        searchbar.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                return handleSearch(searchbar.getText().toString());
            }
        });

        recyclerView=view.findViewById(R.id.productDisplayViewSearch);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter=new ProductAdapter(view.getContext(),productList, new ProductAdapter.ItemClickListener() {
            @Override
            public void onItemClick(Product product) {
                Intent intent =new Intent(getContext(), ProductDetails.class);
                intent.putExtra("Details", product);
                intent.putExtra("Caller","Search");
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
        return view;
    }

    @SuppressLint("NotifyDataSetChanged")
    private boolean handleSearch(String text) {
        productList.clear();
        for (Product p:Product.productList) {
            if(p.getTitle().toLowerCase(Locale.ROOT).contains(text.toLowerCase(Locale.ROOT))||p.getCategory().toLowerCase(Locale.ROOT).contains(text.toLowerCase(Locale.ROOT)))
            {
                productList.add(p);
            }
        }
        adapter.notifyDataSetChanged();
        return true;
    }
}