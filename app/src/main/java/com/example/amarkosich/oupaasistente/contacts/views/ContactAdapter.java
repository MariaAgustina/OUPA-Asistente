package com.example.amarkosich.oupaasistente.contacts.views;


import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.amarkosich.oupaasistente.R;
import com.example.amarkosich.oupaasistente.contacts.Contact;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ContactAdapter extends ArrayAdapter<Contact> {

    private Map<String, Integer> mapContactToImg;

    public ContactAdapter(Context context, ArrayList<Contact> contacts) {
        super(context, 0, contacts);

        mapContactToImg = new HashMap<>();
        mapContactToImg.put("contacto1", R.drawable.contact1);
        mapContactToImg.put("contacto2", R.drawable.contact2);
        mapContactToImg.put("contacto3", R.drawable.contact3);
        mapContactToImg.put("contacto4", R.drawable.contact4);
        mapContactToImg.put("contacto5", R.drawable.contact5);
        mapContactToImg.put("contacto6", R.drawable.contact6);
        mapContactToImg.put("picture1", R.drawable.contact1);
        mapContactToImg.put("picture2", R.drawable.contact2);
        mapContactToImg.put("picture3", R.drawable.contact3);
        mapContactToImg.put("picture4", R.drawable.contact4);
        mapContactToImg.put("picture5", R.drawable.contact5);
        mapContactToImg.put("picture6", R.drawable.contact6);
        mapContactToImg.put("contact1.png", R.drawable.contact1);
        mapContactToImg.put("contact2.png", R.drawable.contact2);
        mapContactToImg.put("contact3.png", R.drawable.contact3);
        mapContactToImg.put("contact4.png", R.drawable.contact4);
        mapContactToImg.put("contact5.png", R.drawable.contact5);
        mapContactToImg.put("contact6.png", R.drawable.contact6);
        mapContactToImg.put("contacto1.png", R.drawable.contact1);
        mapContactToImg.put("contacto2.png", R.drawable.contact2);
        mapContactToImg.put("contacto3.png", R.drawable.contact3);
        mapContactToImg.put("contacto4.png", R.drawable.contact4);
        mapContactToImg.put("contacto5.png", R.drawable.contact5);
        mapContactToImg.put("contacto6.png", R.drawable.contact6);
        mapContactToImg.put("picture1.png", R.drawable.contact1);
        mapContactToImg.put("picture2.png", R.drawable.contact2);
        mapContactToImg.put("picture3.png", R.drawable.contact3);
        mapContactToImg.put("picture4.png", R.drawable.contact4);
        mapContactToImg.put("picture5.png", R.drawable.contact5);
        mapContactToImg.put("picture6.png", R.drawable.contact6);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Contact contact = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.contacts_view, parent, false);
        }
        // Lookup view for data population

        TextView nameTextView = (TextView) convertView.findViewById(R.id.contactName);

        ImageView imageView = (ImageView) convertView.findViewById(R.id.contactImage);
        // Populate the data into the template view using the data object
        nameTextView.setText(contact.name);
        Integer value = mapContactToImg.get(contact.picture);
        imageView.setImageResource(value);


        convertView.setBackgroundColor(ContextCompat.getColor(super.getContext(),R.color.p_takenBg));
//            hourTextView.setTextColor(ContextCompat.getColor(super.getContext(),R.color.p_takenText));
            nameTextView.setTextColor(ContextCompat.getColor(super.getContext(),R.color.p_takenText));
//            imageView.setImageResource(R.drawable.pill_ok);
//            imageView.setVisibility(View.VISIBLE);


        // Return the completed view to render on screen
        return convertView;
    }



}
