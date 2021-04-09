package com.example.ledcontroller;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.switchmaterial.SwitchMaterial;

import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import networking.LedGroup;
import patternEditor.PatternEditor;
import top.defaults.colorpicker.ColorPickerPopup;

public class LightsListView extends RecyclerView.Adapter<LightsListView.LightRow> {

    Context context;
    ArrayList<LedGroup> lights;

    public LightsListView(Context context, ArrayList<LedGroup> lights) {
        this.context = context;
        this.lights = lights;
    }

    @NonNull
    @Override
    public LightRow onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.light_row, parent, false);
        return new LightRow(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LightRow holder, int position) {
        holder.update(lights.get(position));

    }

    @Override
    public int getItemCount() {
        return lights.size();
    }

    public class LightRow extends RecyclerView.ViewHolder {

        LedGroup led_strip;
        TextView name;
        PowerSwitch powerSwitch;
        CardView background;

        View.OnClickListener onClick = view -> {
            new ColorPickerPopup.Builder(context)
                .initialColor(this.led_strip.getColor()) // Set initial color
                .okTitle("Choose")
                .cancelTitle("Cancel")
                .showIndicator(true)
                .showValue(true)
                .build()
                .show(view, new ColorPickerPopup.ColorPickerObserver() {
                            @Override
                            public void onColorPicked(int color) {
                                led_strip.set(color);
                                update();
                            }
                        }
                );
    };

        public LightRow(@NonNull View itemView) {
            super(itemView);
            background = itemView.findViewById(R.id.CardView);
            name = itemView.findViewById(R.id.name);
            powerSwitch = new PowerSwitch(itemView.findViewById(R.id.led_power));
            background.setOnClickListener(onClick);

            Button temp = itemView.findViewById(R.id.Temp);
            temp.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context, PatternEditor.class);
                            context.startActivity(intent);
                        }
                    }
                    );
        }
        /**
         *updates row values without modifying the strip
         */
        public void update(){
            update(led_strip);
        }
        /**
         * updates row values changing the strip.
         */
        public void update(LedGroup new_strip) {
            led_strip = new_strip;
            name.setText(led_strip.getName());
            powerSwitch.update();
            background.setCardBackgroundColor(led_strip.getColor());
        }

    private class PowerSwitch {
        private final SwitchMaterial power_switch;
        private final CompoundButton.OnCheckedChangeListener function = (buttonView, isChecked) ->led_strip.power(isChecked);

        public PowerSwitch(@NonNull SwitchMaterial power_switch){
            this.power_switch = power_switch;
        }

        /**
         * syncs led_switch on value with switch on value.
         */
        public void update(){
            power_switch.setOnCheckedChangeListener((_1,_2) -> {});
            power_switch.setChecked(led_strip.getOn());
            power_switch.setOnCheckedChangeListener(function);
        }
    }
}
}
