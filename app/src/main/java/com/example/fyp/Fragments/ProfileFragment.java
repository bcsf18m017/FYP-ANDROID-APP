package com.example.fyp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.fyp.Dialogs.PasswordDialog;
import com.example.fyp.R;
public class ProfileFragment extends Fragment {

    Button changePasswrod;
    public ProfileFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_profile, container, false);


        changePasswrod=view.findViewById(R.id.user_change_password_profile);
        changePasswrod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PasswordDialog dialog=new PasswordDialog();
                dialog.showDialog(view.getContext());
            }
        });

        return view;
    }
}