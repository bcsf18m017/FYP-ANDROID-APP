package com.example.fyp.Fragments;

import static android.view.ViewGroup.*;

import android.graphics.drawable.Animatable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.fyp.Dialogs.PasswordDialog;
import com.example.fyp.Entities.User;
import com.example.fyp.R;

public class ProfileFragment extends Fragment {

    Button changePasswrod,changeProfile,cancel;
    RelativeLayout r1,r2;
    Animation up,down;
    EditText name,phone,address;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);


        changePasswrod = view.findViewById(R.id.user_change_password_profile);
        changeProfile=view.findViewById(R.id.user_change_profile);
        cancel=view.findViewById(R.id.profile_cancel_button);
        r1=view.findViewById(R.id.views_container);
        r2=view.findViewById(R.id.views_container2);

        name=view.findViewById(R.id.name_change_field);
        phone=view.findViewById(R.id.phone_change_field);
        address=view.findViewById(R.id.address_change_field);

        name.setText(User.user.getName());
        phone.setText(User.user.getPhone());
        address.setText(User.user.getAddress());

        up= AnimationUtils.loadAnimation(getContext(),R.anim.item_animation_rise_up);
        down= AnimationUtils.loadAnimation(getContext(),R.anim.item_animation_fall_down);


        changePasswrod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PasswordDialog dialog = new PasswordDialog();
                dialog.showDialog(view.getContext());
            }
        });

        changeProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                r1.setVisibility(View.GONE);
                r1.startAnimation(up);
                up.setFillAfter(false);
                r2.setVisibility(View.VISIBLE);
                r2.startAnimation(down);
                down.setFillAfter(false);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                r2.setVisibility(View.GONE);
                r2.startAnimation(up);
                up.setFillAfter(false);
                r1.setVisibility(View.VISIBLE);
                r1.startAnimation(down);
                down.setFillAfter(false);
            }
        });
        return view;
    }
}