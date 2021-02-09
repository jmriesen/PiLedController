package com.example.ledcontroller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import networking.LedStrip;
import top.defaults.colorpicker.ColorPickerPopup;

public class LightsListView extends RecyclerView.Adapter<LightsListView.LightRow>{

    Context context;
    ArrayList<LedStrip> lights;
    public LightsListView(Context context, ArrayList<LedStrip> lights){
        this.context = context;
        this.lights = lights;
    }
    @NonNull
    @Override
    public LightRow onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.light_row, parent,false);
        return new LightRow(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LightRow holder, int position) {
        holder.led_strip = lights.get(position);
        holder.update();
    }

    @Override
    public int getItemCount() {
        return lights.size();
    }
    public class LightRow extends RecyclerView.ViewHolder {

        LedStrip led_strip;
        TextView name;
        @SuppressLint("UseSwitchCompatOrMaterialCode")
        Switch power_switch;

        public LightRow(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            power_switch = itemView.findViewById(R.id.led_power);
            power_switch.setOnCheckedChangeListener((buttonView, isChecked) -> led_strip.power(isChecked));
            itemView.setOnClickListener(view -> new ColorPickerPopup.Builder(context)
                    .initialColor(Color.RED) // Set initial color
                    .okTitle("Choose")
                    .cancelTitle("Cancel")
                    .showIndicator(true)
                    .showValue(true)
                    .build()
                    .show(itemView, new ColorPickerPopup.ColorPickerObserver() {
                        @Override
                        public void onColorPicked(int color) {
                            power_switch.setBackgroundColor(color);
                            led_strip.set(color);
                        }
                    })
            );
        }
        public void update(){
            name.setText(led_strip.getName());
        }
    }
}
