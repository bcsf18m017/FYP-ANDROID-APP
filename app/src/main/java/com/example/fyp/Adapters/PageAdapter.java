package com.example.fyp.Adapters;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.fyp.Fragments.CategoriesFragment;
import com.example.fyp.Fragments.HomeFragment;

public class PageAdapter extends FragmentPagerAdapter {
    int tabCount;

    public PageAdapter(@NonNull FragmentManager fm, int count) {
        super(fm);
        tabCount = count;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return new CategoriesFragment(HomeFragment.categories[position]);
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
