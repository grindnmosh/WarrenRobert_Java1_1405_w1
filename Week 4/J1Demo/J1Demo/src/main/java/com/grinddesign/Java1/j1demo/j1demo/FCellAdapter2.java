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

import java.util.ArrayList;

public class FCellAdapter2 extends ArrayAdapter<String> {

    private Context context;
    private ArrayList<String> arrayLister  = MainActivity.testArray3;

    public FCellAdapter2(Context context, int resource, ArrayList<String> arrayLister) {

        super(context, resource, arrayLister);
        Log.i("cust", "adapterdude");
        this.context = context;
        this.arrayLister = arrayLister;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i("cust", "adapterdudette");

        String s = arrayLister.get(position);

        LayoutInflater blowUp = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = blowUp.inflate(R.layout.item_fcell, null);


        TextView tvMain = (TextView) view.findViewById(R.id.name);
        tvMain.setText(s);


        Log.i("cust", "ready to return");
        //return super.getView(position, convertView, parent);
        return view;
    }
}
