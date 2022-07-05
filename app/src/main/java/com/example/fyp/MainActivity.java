package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.fyp.Entities.TransactionHistories;
import com.example.fyp.Entities.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {
    Timer timer;
    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logo = findViewById(R.id.splash_logo);

        boolean up = true;

        if (!up) {
            up = true;
            logo.startAnimation(animate(up));
        } else {
            up = false;
            logo.startAnimation(animate(up));
        }


        Paper.init(this);

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {


                if (Paper.book().contains("phone")) {
                    String url="https://iqbalelectronicswebapi.azurewebsites.net/api/person";
                    String temp=Paper.book().read("phone");
                    temp=Paper.book().read("id");

                    url+="/"+temp;
                    StringRequest request=new StringRequest(Request.Method.GET, url, new com.android.volley.Response.Listener<String>() {
                        @Override
                        public void onResponse(String response1) {
                            try {
                                JSONObject obj = new JSONObject(response1);
                                String id = obj.getString("id");
                                String name = obj.getString("name");
                                String address = obj.getString("address");
                                String phone = obj.getString("email");
                                String cnic = obj.getString("cnic");
                                String image = getString(R.string.Cloudinary)+obj.getString("image");
                                String temp=obj.getString("salary");
                                double salary;
                                if(temp.equals("null"))
                                {
                                    salary=0;
                                }
                                else
                                {
                                    salary= Double.parseDouble(temp);
                                }
                                String pwd =Paper.book().read("password");
                                String createdBy = obj.getString("creatorId");
                                String date=obj.getString("createdOn").split("T")[0];

                                Paper.book().write("phone", phone);
                                Paper.book().write("password", pwd);
                                Paper.book().write("id", id);
                                User u = new User(id, name, address, phone, cnic, image, salary, pwd, date, createdBy);
                                User.user = u;

                                Intent intent = new Intent(MainActivity.this, MainPage.class);
                                intent.putExtra("Caller", "Home");
                                startActivity(intent);
                                finish();
                            } catch (JSONException  e) {
                                e.printStackTrace();
                            }
                        }
                    }, new com.android.volley.Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MainActivity.this, "Temporary Error While Connecting to server", Toast.LENGTH_SHORT).show();
                        }
                    });
                    RequestQueue queue= Volley.newRequestQueue(MainActivity.this);
                    queue.add(request);
                } else {
                    Intent intent = new Intent(MainActivity.this, Login.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 3000);
    }

    private Animation animate(boolean up) {
        Animation anim = AnimationUtils.loadAnimation(this, up ? R.anim.logo_anim : R.anim.logo_anim);
        anim.setInterpolator(new LinearInterpolator()); // for smooth animation
        return anim;
    }
}