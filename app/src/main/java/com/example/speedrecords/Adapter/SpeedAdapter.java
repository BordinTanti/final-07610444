package com.example.speedrecords.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.speedrecords.R;
import com.example.speedrecords.Record;

public class SpeedAdapter extends RecyclerView.Adapter<SpeedAdapter.MyViewHolder> {
    private Context mContext;
    private Record[] mSpeed;


    public SpeedAdapter(Context context, Record[] Speed) {
        this.mContext = context;
        this.mSpeed = Speed;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_speed, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Record speed = mSpeed[position];

        holder.speedKMTextView.setText(speed.speedKM);
        holder.detailTextView.setText(speed.detail);
        if (speed.cal > 80) {
            holder.cowImageView.setImageResource(
                    R.drawable.red_cow
            );
        }
    }

    @Override
    public int getItemCount() {
        return mSpeed.length;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView speedKMTextView;
        TextView detailTextView;
        ImageView cowImageView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            this.speedKMTextView = itemView.findViewById(R.id.speedKMTextView);
            this.detailTextView = itemView.findViewById(R.id.detailTextView);
            this.cowImageView = itemView.findViewById(R.id.cowImageview);

        }
    }
}
