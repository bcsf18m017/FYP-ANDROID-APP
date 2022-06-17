package com.example.fyp.Fragments;

import static com.google.android.material.tabs.TabLayout.MODE_SCROLLABLE;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.fyp.Adapters.PageAdapter;
import com.example.fyp.Adapters.ProductAdapter;
import com.example.fyp.Entities.Category;
import com.example.fyp.Entities.Product;
import com.example.fyp.Entities.User;
import com.example.fyp.MainActivity;
import com.example.fyp.MainPage;
import com.example.fyp.ProductDetails;
import com.example.fyp.R;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import io.paperdb.Paper;

public class HomeFragment extends Fragment {


    TabLayout tabLayout;
    public static List<String>categories;
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
        loadCategories();
        return view;
    }

    //GET PRODUCTS FROM SERVER
    private void createProducts()
    {
        String url="https://iqbalelectronicswebapi.azurewebsites.net/api/products";
        StringRequest request=new StringRequest(Request.Method.GET, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response1) {
                try {
                    JSONArray arr=new JSONArray(response1);
                    for(int i=0;i<arr.length();i++)
                    {
                        JSONObject obj=arr.getJSONObject(i);
                        String id=obj.getString("id");
                        String title=obj.getString("title");
                        String description=obj.getString("description");
                        String image=getString(R.string.Cloudinary)+obj.getString("image");
                        String createdBy=obj.getString("createdBy");
                        int minimumInstallment=obj.getInt("minimumInstallments");
                        boolean daily=obj.getBoolean("daily");
                        boolean monthly=obj.getBoolean("monthly");
                        double price=obj.getDouble("price");
                        double percentage=obj.getDouble("percentage");
                        //Category
                        JSONObject obj1=obj.getJSONObject("category");
                        String category=obj1.getString("description");

                        Product p=new Product(id,title,description,category,image,createdBy,price,percentage,minimumInstallment,daily,monthly);
                        Product.productList.add(p);
                    }
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



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Temporary Error While Connecting to server", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue queue= Volley.newRequestQueue(requireContext());
        queue.add(request);
    }

    //ADD TABS TO TABLAYOUT
    private void addTabs() {

        for (int i = 0; i < categories.size(); i++) {
            tabLayout.addTab(tabLayout.newTab().setText(categories.get(i)));
        }
        tabLayout.setTabMode(MODE_SCROLLABLE);
        createProducts();
    }

    //GET CATEGORIES FROM SERVER
    public void loadCategories()
    {
        categories=new ArrayList<>();
        String url="https://iqbalelectronicswebapi.azurewebsites.net/api/categories";
        StringRequest request=new StringRequest(Request.Method.GET, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response1) {
                try {
                    JSONArray arr=new JSONArray(response1);
                    for(int i=0;i<arr.length();i++)
                    {
                        JSONObject obj=arr.getJSONObject(i);
                        categories.add(obj.getString("description"));
                    }
                    addTabs();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Temporary Error While Connecting to server", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue queue= Volley.newRequestQueue(requireContext());
        queue.add(request);
    }


}