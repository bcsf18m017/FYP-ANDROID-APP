package com.example.fyp.Dialogs;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fyp.R;

public class ConfirmOrderDialog {
    @SuppressLint("SetTextI18n")
    public void showDialog(Context activity,  double dailyAmount, double monthlyAmount) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.confirm_order_layout);
        TextView daily = dialog.findViewById(R.id.dailyHeading);
        TextView monthly = dialog.findViewById(R.id.monthlyHeading);

        daily.setText("Your Daily Installment Will Be "+Double.toString(dailyAmount));
        monthly.setText("Your Monthly Installment Will Be "+Double.toString(monthlyAmount));



        Button cancel = dialog.findViewById(R.id.order_cancel_button);
        Button confirm = dialog.findViewById(R.id.place_order_button);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity, "Confirm", Toast.LENGTH_SHORT).show();

            }
        });
        dialog.show();
    }
}
