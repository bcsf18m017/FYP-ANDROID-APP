package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;

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
                    Intent intent=new Intent(Login.this,MainPage.class);
                    startActivity(intent);
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