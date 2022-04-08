package com.example.fyp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fyp.R;

import java.util.ArrayList;

public class SpinnerItemAdapter extends BaseAdapter {
    Context context;
    ArrayList<String>optionNames;

    public SpinnerItemAdapter(Context context, ArrayList<String> categoryNames) {
        this.context = context;
        this.optionNames = categoryNames;
    }

    @Override
    public int getCount() {
        return optionNames.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView= LayoutInflater.from(context).inflate(R.layout.spinner_layout,parent,false);
        TextView txt=convertView.findViewById(R.id.spinnerText);
        txt.setText(optionNames.get(position));

        return convertView;
    }
}
