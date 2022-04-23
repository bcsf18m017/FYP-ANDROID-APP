package com.example.fyp.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fyp.R;

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
                Toast.makeText(activity, "Update", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
    }
}
