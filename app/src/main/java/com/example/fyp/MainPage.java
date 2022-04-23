package com.example.fyp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fyp.Entities.User;
import com.example.fyp.Fragments.CartFragment;
import com.example.fyp.Fragments.HomeFragment;
import com.example.fyp.Fragments.ProfileFragment;
import com.example.fyp.Fragments.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import io.paperdb.Paper;

public class MainPage extends AppCompatActivity {

    ImageView menu, logout;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;
    FragmentManager fm = getSupportFragmentManager();
    final Fragment home = new HomeFragment();
    final Fragment profile = new ProfileFragment();
    Fragment cart = new CartFragment();
    final Fragment search = new SearchFragment();
    Fragment active = home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        setContentView(R.layout.activity_main_page);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        logout = toolbar.findViewById(R.id.toolbar_logout);
        menu = toolbar.findViewById(R.id.toolbar_menu);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.side_nav_drawer);
        bottomNavigationView = findViewById(R.id.bottom_nav_bar);

        Paper.init(this);

        menu.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(Gravity.START);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Paper.book().destroy();
                Intent intent = new Intent(MainPage.this, Login.class);
                startActivity(intent);
                finish();
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

        View header = navigationView.getHeaderView(0);
        TextView text = header.findViewById(R.id.nav_user_name);
        text.setText(User.user.getName());
        ImageView imageView = header.findViewById(R.id.nav_profile_image);
        imageView.setImageResource(R.drawable.noman);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(profile);
                drawerLayout.closeDrawer(Gravity.LEFT);
                bottomNavigationView.setSelectedItemId(R.id.bottom_profile);

            }
        });


        fm.beginTransaction().add(R.id.main_linear_layout, search, "search").hide(search).commit();
        fm.beginTransaction().add(R.id.main_linear_layout, cart, "cart").hide(cart).commit();
        fm.beginTransaction().add(R.id.main_linear_layout, profile, "profile").hide(profile).commit();
        fm.beginTransaction().add(R.id.main_linear_layout, home, "home").commit();


        try {
            String caller = getIntent().getStringExtra("Caller").toString();
            if (caller.equals("Cart")) {
                bottomNavigationView.setSelectedItemId(R.id.bottom_cart);
                loadFragment(cart);
            } else if (caller.equals("Home")) {
                bottomNavigationView.setSelectedItemId(R.id.bottom_home);
                loadFragment(home);
            } else if (caller.equals("Search")) {
                bottomNavigationView.setSelectedItemId(R.id.bottom_search);
                loadFragment(search);
            }
        } catch (Exception e) {

        }
    }

    private boolean sideBarItemListenerHandler(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.team:
                startActivity(new Intent(MainPage.this, TeamInfo.class));
                drawerLayout.closeDrawer(Gravity.LEFT);
                return true;
            case R.id.orders:
                Toast.makeText(MainPage.this, "HELP", Toast.LENGTH_SHORT).show();
                return true;
        }
        return false;
    }

    @SuppressLint("NonConstantResourceId")
    private boolean bottomNavItemListenerHandler(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.bottom_home:
                loadFragment(home);
                return true;
            case R.id.bottom_cart:
                loadFragment(cart);
                return true;
            case R.id.bottom_phone:
                String phone = "03024677533";
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(intent);
            case R.id.bottom_search:
                loadFragment(search);
                return true;
            case R.id.bottom_profile:
                loadFragment(profile);
                return true;
        }
        return true;
    }


    private void loadFragment(Fragment fragment)
    {
        fm.beginTransaction().hide(active).show(fragment).commit();
        active=fragment;
    }
}