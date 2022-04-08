package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.fyp.Entities.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.paperdb.Paper;

public class Login extends AppCompatActivity {

    Button login;
    EditText phone,password;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        dialog=new Dialog(this);
        login=findViewById(R.id.login_button);
        phone=findViewById(R.id.login_phone);
        password=findViewById(R.id.login_password);

        Paper.init(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneText=phone.getText().toString();
                String pwdText=password.getText().toString();
                if(TextUtils.isEmpty(phoneText))
                {
                    phone.setError("Please Enter Phone");
                }
                else if(TextUtils.isEmpty(pwdText))
                {
                    password.setError("Please Enter Password");
                }
                else if(phoneText.equals("123")&& pwdText.equals("123"))
                {
                    String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                    @SuppressLint("SimpleDateFormat") SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
                    Date date1= null;
                    try {
                        date1 = f.parse(date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    User u=new User("1","NOMAN TAHIR","H#7,St#15 Ramgarh Main Bazaar Mughalpura Lahore","03024677533","3520126349331",R.drawable.user,130000.0,"123",date1,"NOMI");
                    User.user=u;
                    Paper.book().write("phone","03024677533");
                    Paper.book().write("password","123");
                    Intent intent=new Intent(Login.this,MainPage.class);
                    intent.putExtra("Caller","Home");
                    startActivity(intent);
                    finish();
                }
                else
                {
                    showDialog();
                }
            }
        });
    }
    private void showDialog() {
        dialog.setContentView(R.layout.invalid_credentials_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        dialog.setCancelable(false);
        Button okBtn=dialog.findViewById(R.id.dialog_button_ok);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}