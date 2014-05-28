package com.grinddesign.Java1.j1demo.j1demo;

//Robert Warren
//Java 1
//Term 1405


import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.loopj.android.image.SmartImageView;

import java.util.ArrayList;

public class CellAdapter extends ArrayAdapter<String> {
    //public static ArrayList testArray;
    //public static ArrayList<String> image;
    private Context context;
    private ArrayList<String> arrayLister  = MainActivity.testArray;
    public CellAdapter(Context context, int resource, ArrayList<String> arrayLister) {

        super(context, resource, arrayLister);
        Log.i("cust", "adapter1");
        this.context = context;
        this.arrayLister = arrayLister;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i("cust", "adapter");

        String i = MainActivity.image.get(position);
        String s = arrayLister.get(position);

        LayoutInflater blowUp = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = blowUp.inflate(R.layout.item_cell, null);

        SmartImageView myImage = (SmartImageView) view.findViewById(R.id.my_image);
        myImage.setImageUrl(i);

        TextView tvMain = (TextView) view.findViewById(R.id.post);
        tvMain.setText(s);


        Log.i("cust", "ready to return");
        //return super.getView(position, convertView, parent);
        return view;
    }
}