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
import android.widget.Button;
import android.widget.Toast;

import com.example.fyp.Adapters.CartAdapter;
import com.example.fyp.DB.CartDB;
import com.example.fyp.Dialogs.ConfirmOrderDialog;
import com.example.fyp.Entities.Cart;
import com.example.fyp.Entities.Product;
import com.example.fyp.R;

import java.util.List;


public class CartFragment extends Fragment {

    private RecyclerView recyclerView;
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    Button total,confirm;

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

        confirm=view.findViewById(R.id.confirmOrderButton);

        recyclerView = view.findViewById(R.id.cartDisplayView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CartAdapter(getContext(), Cart.cartList);
        recyclerView.setAdapter(adapter);


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CartDB db=new CartDB(getContext());
                List<Cart> list=db.getAllItems();
                double daily=0,monthly=0;
                for(int i=0;i<list.size();i++)
                {
                    for (Product p:Product.productList) {
                        if(list.get(i).getProduct_id().equals(p.getProduct_ID())) {
                            double price = (p.getPrice() + ((p.getPercentage() * p.getPrice()) / 100)) * list.get(i).getQuantity();
                            if (list.get(i).getPayment_method().equals("Daily")) {
                                daily+= price/p.getMinimumInstallments()/30;
                            }
                            else
                            {
                                monthly+=price/p.getMinimumInstallments();
                            }

                        }
                    }
                }
                ConfirmOrderDialog confirmOrderDialog=new ConfirmOrderDialog();
                confirmOrderDialog.showDialog(getContext(),daily,monthly);

            }
        });

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