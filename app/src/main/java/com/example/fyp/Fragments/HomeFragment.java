package com.example.fyp.Fragments;

import static com.google.android.material.tabs.TabLayout.MODE_SCROLLABLE;

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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HomeFragment extends Fragment {


    TabLayout tabLayout;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    List<Product> productList = new ArrayList<>();
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_home, container, false);
        tabLayout=view.findViewById(R.id.tabLayout);

        addTabs();


        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        @SuppressLint("SimpleDateFormat") SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
        Date date1= null;
        try {
            date1 = f.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Product p=new Product("123","Product1","description","category2", R.drawable.user,"NOMAN",20,20,true,true,date1);
        Product p1=new Product("234","Product2","description","category1", R.drawable.cross,"NOMAN",30,20,true,true,date1);
        Product p2=new Product("456","Product3","description","category1", R.drawable.logo,"NOMAN",40,20,true,true,date1);
        Product p3=new Product("789","Product4","description","category2", R.drawable.splashimage,"NOMAN",50,20,true,true,date1);
        Product p4=new Product("101","Product5","description","category3", R.drawable.ic_baseline_supervised_user_circle_24,"NOMAN",60,20,true,true,date1);
        Product p5=new Product("102","Product6","description","category2", R.drawable.ie,"NOMAN",60,20,true,true,date1);
        Product p6=new Product("103","Product7","description","category1", R.drawable.ie,"NOMAN",60,20,true,true,date1);
        Product p7=new Product("104","Product8","description","category3",R.drawable.addproductimage,"NOMAN",60,20,true,true,date1);

        Product.productList.add(p);
        Product.productList.add(p1);
        Product.productList.add(p2);
        Product.productList.add(p3);
        Product.productList.add(p4);
        Product.productList.add(p5);
        Product.productList.add(p6);
        Product.productList.add(p7);

        productList=new ArrayList<>(Product.productList);

        recyclerView=view.findViewById(R.id.productDisplayView);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter=new ProductAdapter(view.getContext(), productList, new ProductAdapter.ItemClickListener() {
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

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                    tabListener(tab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return view;
    }

    private void addTabs()
    {
        tabLayout.addTab(tabLayout.newTab().setText("all"));
        tabLayout.addTab(tabLayout.newTab().setText("category1"));
        tabLayout.addTab(tabLayout.newTab().setText("category2"));
        tabLayout.addTab(tabLayout.newTab().setText("category3"));
        tabLayout.addTab(tabLayout.newTab().setText("category4"));
        tabLayout.setTabMode(MODE_SCROLLABLE );
    }
    @SuppressLint("NotifyDataSetChanged")
    private void tabListener(TabLayout.Tab tab)
    {
        tabLayout.selectTab(tab);
        productList.clear();
        for (Product p:Product.productList) {
            if(p.getCategory().equals(tab.getText()) || tab.getPosition()==0)
            {
                productList.add(p);
            }
        }
        adapter.notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }



}