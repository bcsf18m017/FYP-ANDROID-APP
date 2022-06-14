package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.TableLayout;

import com.example.fyp.Adapters.OrdersVPAdapter;
import com.example.fyp.Fragments.ActiveOrder;
import com.example.fyp.Fragments.OrderHistory;
import com.google.android.material.tabs.TabLayout;

public class Orders extends AppCompatActivity {



    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        tabLayout=findViewById(R.id.order_tab_layout);
        viewPager=findViewById(R.id.order_view_pager);

        tabLayout.setupWithViewPager(viewPager);

        OrdersVPAdapter adapter=new OrdersVPAdapter(getSupportFragmentManager());
        adapter.addfragment(new ActiveOrder(),"Active");
        adapter.addfragment(new OrderHistory(),"History");

        viewPager.setAdapter(adapter);

    }
}