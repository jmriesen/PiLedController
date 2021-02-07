package com.example.ledcontroller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import networking.LedStrip;
import networking.VollyController;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    Context context;
    ArrayList<LedStrip> lights;
    public MyAdapter(Context context, ArrayList<LedStrip> lights){
        this.context = context;
        this.lights = lights;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.light_row, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.led_strip = lights.get(position);
        holder.update();
    }

    @Override
    public int getItemCount() {
        return lights.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        LedStrip led_strip;
        TextView name;
        Switch aSwitch;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            aSwitch = itemView.findViewById(R.id.led_power);
            aSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> led_strip.power(isChecked));
        }
        public void update(){
            name.setText(led_strip.getName());
        }
    }
}
