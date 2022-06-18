package com.example.fyp.Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fyp.Api.ApiInterface;
import com.example.fyp.Api.RetrofitClient;
import com.example.fyp.Entities.User;
import com.example.fyp.Login;
import com.example.fyp.MainPage;
import com.example.fyp.R;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PasswordDialog {

    public void showDialog(Context activity) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.password_change_dialog);
        EditText old = dialog.findViewById(R.id.old_password);
        EditText new1 = dialog.findViewById(R.id.new_password1);
        EditText new2 = dialog.findViewById(R.id.new_password2);

        Button cancel = dialog.findViewById(R.id.password_cancel_button);
        Button update = dialog.findViewById(R.id.password_update_button);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(new1.getText().toString().equals(new2.getText().toString()))
                {
                    ApiInterface apiInterface= RetrofitClient.getRetrofit().create(ApiInterface.class);
                    String oldText=old.getText().toString();
                    Call<String> call=apiInterface.changePassword(User.user.getId(), oldText,new1.getText().toString());
                    call.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            if(response.errorBody()==null)
                            {
                                Toast.makeText(activity,"Password Changed Successfully",Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                                Paper.book().destroy();
                                Intent intent = new Intent(activity, Login.class);
                                activity.startActivity(intent);
                                ((Activity)activity).finish();
                            }
                            else
                            {
                                Toast.makeText(activity,"Current Password is Incorrect",Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }

                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Toast.makeText(activity,"Temporary Server Error",Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    });
                }
                else
                {
                    Toast.makeText(activity,"Password Mismatch",Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.show();
    }
}
