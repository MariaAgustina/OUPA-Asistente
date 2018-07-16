package com.example.amarkosich.oupaasistente.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.amarkosich.oupaasistente.R;
import com.example.amarkosich.oupaasistente.model.UserLogged;

public class OupaViewHolder extends RecyclerView.ViewHolder {
    public TextView name, email;

    public OupaViewHolder(View view) {
        super(view);
        name = view.findViewById(R.id.name);
        email = view.findViewById(R.id.email);
    }

    public void bind(final UserLogged userLogged, final OupaAdapter.OnItemClickListener listener) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                listener.onItemClick(userLogged);
            }
        });
    }
}