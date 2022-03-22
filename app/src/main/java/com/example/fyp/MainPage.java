package com.example.fyp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainPage extends AppCompatActivity {

    ImageView menu,wallet;
    EditText searchbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        wallet=toolbar.findViewById(R.id.toolbar_wallet);
        menu=toolbar.findViewById(R.id.toolbar_menu);
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.side_nav_drawer);
        bottomNavigationView=findViewById(R.id.bottom_nav_bar);


        menu.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(Gravity.START);
            }
        });
        wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainPage.this,"Wallet",Toast.LENGTH_SHORT).show();
            }
        });


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               return sideBarItemListenerHandler(item);
            }
        });
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               return bottomNavItemListenerHandler(item);
            }
        });

        HomeFragment home=new HomeFragment();
        FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_linear_layout, home);
        transaction.commit();
    }

    private boolean sideBarItemListenerHandler(MenuItem item){
        switch (item.getItemId())
        {
            case R.id.terms:
                Toast.makeText(MainPage.this, "TERMS", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.help:
                Toast.makeText(MainPage.this, "HELP", Toast.LENGTH_SHORT).show();
                return true;
        }
        return false;
    }
    private boolean bottomNavItemListenerHandler(MenuItem item){
        switch (item.getItemId())
        {
            case R.id.bottom_home:
                HomeFragment home=new HomeFragment();
                FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.main_linear_layout, home);
                transaction.commit();
                return true;
            case R.id.bottom_cart:
                CartFragment cart=new CartFragment();
                FragmentTransaction transaction1 =getSupportFragmentManager().beginTransaction();
                transaction1.replace(R.id.main_linear_layout, cart);
                transaction1.commit();
                return true;
        }
        return false;
    }

}