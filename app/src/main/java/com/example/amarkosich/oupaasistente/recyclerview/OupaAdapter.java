package com.example.amarkosich.oupaasistente.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amarkosich.oupaasistente.R;
import com.example.amarkosich.oupaasistente.model.UserLogged;

import java.util.List;

public class OupaAdapter extends RecyclerView.Adapter<OupaViewHolder> {

    private List<UserLogged> oupaList;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(UserLogged item);
    }

    public OupaAdapter(List<UserLogged> oupaList, OnItemClickListener onItemClickListener) {
        this.oupaList = oupaList;
        this.listener = onItemClickListener;
    }

    @Override
    public OupaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.oupa_selector_row, parent, false);

        return new OupaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(OupaViewHolder holder, int position) {
        UserLogged user = oupaList.get(position);
        holder.name.setText(user.firstName + " " + user.lastName);
        holder.email.setText(user.email);
        holder.bind(oupaList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return oupaList.size();
    }

}