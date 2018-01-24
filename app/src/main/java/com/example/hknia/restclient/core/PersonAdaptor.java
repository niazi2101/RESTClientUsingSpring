package com.example.hknia.restclient.core;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.hknia.restclient.R;

import java.util.ArrayList;

/**
 * Created by hknia on 1/24/2018.
 */

public class PersonAdaptor extends ArrayAdapter<Person> {


    public PersonAdaptor(@NonNull Context context, ArrayList<Person> persons) {
        super(context, 0, persons);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        // Get the data item for this position
        Person person = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.getall_listitem, parent, false);
        }

        // Lookup view for data population
        TextView tvPersonID = (TextView) view.findViewById(R.id.tvPersonID);
        TextView tvNameL = (TextView) view.findViewById(R.id.tvNameL);
        TextView tvPayRate = (TextView) view.findViewById(R.id.tvPayRateL);

        // Extract properties from cursor
        long reg = person.getID();
        String name = person.getFirstName() + " " + person.getLastName();
        String payRate = "Pay Rate: "+ person.getPayRate();

        // Populate fields with extracted properties
        tvPersonID.setText(String.valueOf(reg));
        tvNameL.setText(name);
        tvPayRate.setText(payRate);

        return view;
    }
}
