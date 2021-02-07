package com.example.ledcontroller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    Context context;
    String[] lights;
    public MyAdapter(Context context, String[] lights){
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
        holder.led_name = lights[position];
        holder.update();
    }

    @Override
    public int getItemCount() {
        return lights.length;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        String led_name = "";
        TextView name;
        Switch aSwitch;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            aSwitch = itemView.findViewById(R.id.led_power);
            aSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
                VollyController.power(led_name,isChecked);
            });
        }
        public void update(){
            name.setText(led_name);
        }
    }
}
