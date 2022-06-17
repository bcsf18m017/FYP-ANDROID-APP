package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.fyp.Api.ApiInterface;
import com.example.fyp.Api.RetrofitClient;
import com.example.fyp.Entities.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    Button login;
    EditText phone, password;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        dialog=new ProgressDialog(Login.this);

        ApiInterface apiInterface= RetrofitClient.getRetrofit().create(ApiInterface.class);
        login = findViewById(R.id.login_button);
        phone = findViewById(R.id.login_phone);
        password = findViewById(R.id.login_password);

        Paper.init(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneText = phone.getText().toString();
                String pwdText = password.getText().toString();
                if (TextUtils.isEmpty(phoneText)) {
                    phone.setError("Please Enter Phone");
                } else if (TextUtils.isEmpty(pwdText)) {
                    password.setError("Please Enter Password");
                }
                else {
                    dialog.setTitle("Sign In");
                    dialog.setMessage("Please wait while we are signing you in.");
                    dialog.setCancelable(false);
                    dialog.show();
                    Call<User> call = apiInterface.postLogin(new com.example.fyp.Entities.Login(phoneText, password.getText().toString()));
                    call.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            if (response.body() == null) {
                                dialog.dismiss();
                                Toast.makeText(Login.this, "Invalid Phone or Password", Toast.LENGTH_SHORT).show();
                            } else {

                                String url="https://iqbalelectronicswebapi.azurewebsites.net/api/person";
                                url+="/"+response.body().getId();
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
                                            String pwd =password.getText().toString();
                                            String createdBy = obj.getString("creatorId");
                                            String date=obj.getString("createdOn").split("T")[0];
                                            Date createdOn =new SimpleDateFormat("yyyy-mm-dd").parse(date);;

                                            Paper.book().write("phone", phone);
                                            Paper.book().write("password", pwd);
                                            Paper.book().write("id", id);
                                            User u = new User(id, name, address, phone, cnic, image, salary, pwd, createdOn, createdBy);
                                            User.user = u;

                                            JSONArray arr=obj.getJSONArray("orders");
                                            JSONArray arr1=obj.getJSONArray("transactionHistories");

                                            Intent intent = new Intent(Login.this, MainPage.class);
                                            intent.putExtra("Caller", "Home");
                                            startActivity(intent);
                                            finish();
                                        } catch (JSONException | ParseException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }, new com.android.volley.Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                }
                                );
                                RequestQueue queue= Volley.newRequestQueue(Login.this);
                                queue.add(request);

                            }
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            dialog.dismiss();
                            Toast.makeText(Login.this, "Temporary error while connecting to server", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}