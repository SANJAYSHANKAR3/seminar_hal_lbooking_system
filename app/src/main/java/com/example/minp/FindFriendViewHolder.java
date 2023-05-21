package com.example.minp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FindFriendViewHolder extends RecyclerView.ViewHolder {
    TextView name;
    public FindFriendViewHolder(@NonNull View itemView) {

        super(itemView);
        name=itemView.findViewById(R.id.name);
    }
}
