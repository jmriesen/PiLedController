package com.example.ledcontroller;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

public class VollyController {
    private static final String url = "http://10.36.116.36:8000/";
    private static RequestQueue requestQueue;

    public static void init(Context context){
        if (requestQueue ==null){
            requestQueue  = Volley.newRequestQueue(context);
        }
    }

    public static RequestQueue get(){
        return requestQueue;
    }
    public static String getUrl(){
        return url;
    }

    public static void power(String name,boolean on){
        String power =on?"on":"off";
        requestQueue.add(
                new JsonObjectRequest(Request.Method.GET, VollyController.getUrl() + power, null, null, (Throwable::printStackTrace)));
    }
}
