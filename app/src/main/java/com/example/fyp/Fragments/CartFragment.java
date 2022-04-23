package com.example.fyp.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fyp.Adapters.CartAdapter;
import com.example.fyp.DB.CartDB;
import com.example.fyp.Entities.Cart;
import com.example.fyp.R;


public class CartFragment extends Fragment {

    private RecyclerView recyclerView;
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;


    public CartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        CartDB cartDb = new CartDB(getContext());
        Cart.populateList(cartDb.getAllItems());

        recyclerView = view.findViewById(R.id.cartDisplayView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CartAdapter(getContext(), Cart.cartList);
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            cartLoader();
        }
    }

    private void cartLoader() {
        CartDB cartDb = new CartDB(getContext());
        Cart.populateList(cartDb.getAllItems());
        recyclerView = getActivity().findViewById(R.id.cartDisplayView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CartAdapter(getContext(), Cart.cartList);
        recyclerView.setAdapter(adapter);
    }

}