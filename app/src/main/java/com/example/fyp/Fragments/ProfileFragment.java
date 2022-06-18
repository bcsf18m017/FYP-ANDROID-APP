package com.example.fyp.Fragments;

import static android.view.ViewGroup.*;

import android.Manifest;
import android.app.Person;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.graphics.drawable.Animatable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cloudinary.android.MediaManager;
import com.example.fyp.Api.ApiInterface;
import com.example.fyp.Api.RetrofitClient;
import com.example.fyp.Dialogs.PasswordDialog;
import com.example.fyp.Entities.PersonRequest;
import com.example.fyp.Entities.User;
import com.example.fyp.MainActivity;
import com.example.fyp.R;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {

    Button changePassword,changeProfile,cancel,update;
    RelativeLayout r1,r2;
    Animation up,down;
    EditText name,phone,address;
    ImageView image;
    ProgressDialog dialog;
    TextView userName,userPhone,userCnic,userAddress;
    public ProfileFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);


        userName=view.findViewById(R.id.user_name_profile);
        userPhone=view.findViewById(R.id.user_phone_profile);
        userAddress=view.findViewById(R.id.user_address_profle);
        userCnic=view.findViewById(R.id.user_cnic_profile);
        changePassword = view.findViewById(R.id.user_change_password_profile);
        changeProfile=view.findViewById(R.id.user_change_profile);
        cancel=view.findViewById(R.id.profile_cancel_button);
        update=view.findViewById(R.id.profile_update_button);
        r1=view.findViewById(R.id.views_container);
        r2=view.findViewById(R.id.views_container2);
        image=view.findViewById(R.id.user_image_profile);
        dialog=new ProgressDialog(getContext());

        Picasso.get().load(User.user.getImage_id()).into(image);


        name=view.findViewById(R.id.name_change_field);
        phone=view.findViewById(R.id.phone_change_field);
        address=view.findViewById(R.id.address_change_field);

        userName.setText(User.user.getName());
        userPhone.setText(User.user.getPhone());
        userAddress.setText(User.user.getAddress());
        userCnic.setText(User.user.getCnic());

        name.setText(User.user.getName());
        phone.setText(User.user.getPhone());
        address.setText(User.user.getAddress());

        up= AnimationUtils.loadAnimation(getContext(),R.anim.item_animation_rise_up);
        down= AnimationUtils.loadAnimation(getContext(),R.anim.item_animation_fall_down);


        changePassword.setOnClickListener(new View.OnClickListener() {
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
        update.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                dialog.setTitle("Update Profile");
                dialog.setMessage("Please wait while we are updating your profile");
                dialog.setCancelable(false);
                dialog.show();
                changeProfile();
            }
        });
        return view;
    }
    private void changeProfile()
    {
        if(name.getText().equals("")||phone.getText().equals("")||address.getText().equals(""))
        {
            Toast.makeText(getContext(), "Please Provide All Information", Toast.LENGTH_SHORT).show();
        }
        else if(phone.getText().length()!=11)
        {
            Toast.makeText(getContext(), "Phone Number is not correct", Toast.LENGTH_SHORT).show();
        }
        else
        {
            ApiInterface apiInterface= RetrofitClient.getRetrofit().create(ApiInterface.class);
            Call<User>call=apiInterface.changeProfile(User.user.getId(),name.getText().toString(),address.getText().toString());
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if(response.errorBody()==null)
                    {
                        User.user.setName(response.body().getName());
                        User.user.setAddress(response.body().getAddress());
                        Toast.makeText(getContext(), "Profile Updated Successfully", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        r2.setVisibility(View.GONE);
                        r2.startAnimation(up);
                        up.setFillAfter(false);
                        r1.setVisibility(View.VISIBLE);
                        r1.startAnimation(down);
                        down.setFillAfter(false);
                        userName.setText(User.user.getName());
                        userPhone.setText(User.user.getPhone());
                        userAddress.setText(User.user.getAddress());
                        userCnic.setText(User.user.getCnic());
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(getContext(), "Temporary Error Connecting to server", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });
        }

    }


}