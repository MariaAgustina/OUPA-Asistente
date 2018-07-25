package com.example.amarkosich.oupaasistente.pillbox.views;


import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.amarkosich.oupaasistente.R;
import com.example.amarkosich.oupaasistente.pillbox.model.Pill;

import java.util.ArrayList;


public class PillAdapter extends ArrayAdapter<Pill> {

    public PillAdapter(Context context, ArrayList<Pill> pills) {
        super(context, 0, pills);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Pill pill = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.pill_view, parent, false);
        }
        // Lookup view for data population

        TextView hourTextView = (TextView) convertView.findViewById(R.id.pillTime);
        TextView nameTextView = (TextView) convertView.findViewById(R.id.pillTitle);
        //ImageView imageView = (ImageView) convertView.findViewById(R.id.pillImage);

        // Populate the data into the template view using the data object
        hourTextView.setText(pill.hourString());
        nameTextView.setText(pill.name);

        if(pill.drinked){
            convertView.setBackgroundColor(ContextCompat.getColor(super.getContext(),R.color.p_takenBg));
            hourTextView.setTextColor(ContextCompat.getColor(super.getContext(),R.color.p_takenText));
            nameTextView.setTextColor(ContextCompat.getColor(super.getContext(),R.color.p_takenText));

        }else if(pill.shouldBeDrinked()){
            convertView.setBackgroundColor(ContextCompat.getColor(super.getContext(),R.color.p_notTakenBg));
            hourTextView.setTextColor(ContextCompat.getColor(super.getContext(),R.color.p_notTakenText));
            nameTextView.setTextColor(ContextCompat.getColor(super.getContext(),R.color.p_notTakenText));
        }else{
            convertView.setBackgroundColor(ContextCompat.getColor(super.getContext(),R.color.p_futureBg));
            hourTextView.setTextColor(ContextCompat.getColor(super.getContext(),R.color.p_futureText));
            nameTextView.setTextColor(ContextCompat.getColor(super.getContext(),R.color.p_futureText));

        }

        // Return the completed view to render on screen
        return convertView;
    }



}
