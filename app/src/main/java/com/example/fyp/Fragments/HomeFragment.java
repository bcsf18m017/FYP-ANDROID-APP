package com.example.fyp.Fragments;

import static com.google.android.material.tabs.TabLayout.MODE_SCROLLABLE;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fyp.Adapters.PageAdapter;
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
    public static String[] categories = {"category1", "category2", "category3"};
    ViewPager pager;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        tabLayout = view.findViewById(R.id.tabLayout);
        pager = view.findViewById(R.id.viewpager);

        addTabs();


        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        @SuppressLint("SimpleDateFormat") SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = null;
        try {
            date1 = f.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Product p = new Product("123", "Product1", "description", "category1", R.drawable.user, "NOMAN", 20, 20, true, true, date1);
        Product p1 = new Product("234", "Product2", "description", "category1", R.drawable.lcd, "NOMAN", 30, 20, true, true, date1);
        Product p2 = new Product("456", "Product3", "description", "category1", R.drawable.logo, "NOMAN", 40, 20, true, true, date1);
        Product p3 = new Product("789", "Product4", "description", "category1", R.drawable.splashimage, "NOMAN", 50, 20, true, true, date1);
        Product p4 = new Product("101", "Product5", "description", "category3", R.drawable.ic_baseline_supervised_user_circle_24, "NOMAN", 60, 20, true, true, date1);
        Product p5 = new Product("102", "Product6", "description", "category1", R.drawable.ie, "NOMAN", 60, 20, true, true, date1);
        Product p6 = new Product("103", "Product7", "description", "category1", R.drawable.ie, "NOMAN", 60, 20, true, true, date1);
        Product p7 = new Product("104", "Product8", "description", "category3", R.drawable.addproductimage, "NOMAN", 60, 20, true, true, date1);

        Product.productList.add(p);
        Product.productList.add(p1);
        Product.productList.add(p2);
        Product.productList.add(p3);
        Product.productList.add(p4);
        Product.productList.add(p5);
        Product.productList.add(p6);
        Product.productList.add(p7);

        PageAdapter pageAdapter = new PageAdapter(getActivity().getSupportFragmentManager(), tabLayout.getTabCount());
        pager.setAdapter(pageAdapter);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
                pageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        return view;
    }

    private void addTabs() {

        for (int i = 0; i < categories.length; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(categories[i]));
        }
        tabLayout.setTabMode(MODE_SCROLLABLE);
    }

}