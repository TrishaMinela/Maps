package com.example.templemaps;

//this is for the List activity

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class TempleAdapter extends RecyclerView.Adapter<TempleAdapter.TempleViewHolder> {

    private List<Temple> temples;

    public TempleAdapter(List<Temple> temples) {
        this.temples = temples;
    }

    @NonNull
    @Override
    public TempleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.temples_item_list, parent, false);
        return new TempleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TempleViewHolder holder, int position) {
        Temple temple = temples.get(position);
        holder.templeNameTextView.setText(temple.getName());
        holder.templeDescriptionTextView.setText(temple.getDescription());
        holder.templeImageView.setImageResource(temple.getIconResourceId());
    }

    @Override
    public int getItemCount() {
        return temples.size();
    }

    static class TempleViewHolder extends RecyclerView.ViewHolder {
        TextView templeNameTextView;
        TextView templeDescriptionTextView;
        ImageView templeImageView;

        public TempleViewHolder(View itemView) {
            super(itemView);
            templeNameTextView = itemView.findViewById(R.id.templeName);
            templeDescriptionTextView = itemView.findViewById(R.id.description);
            templeImageView = itemView.findViewById(R.id.imageview);
        }
    }
}