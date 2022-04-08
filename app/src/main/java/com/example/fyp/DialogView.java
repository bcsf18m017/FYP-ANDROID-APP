package com.example.fyp;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class DialogView {
    public void showDialog(Context activity, String msg,int imageID){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_layout);
        TextView text = (TextView) dialog.findViewById(R.id.dialog_text);
        text.setText(msg);
        ImageView image=(ImageView)dialog.findViewById(R.id.dialog_image);
        image.setImageResource(imageID);
        dialog.show();

    }
}
