package networking;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ledcontroller.R;

public class VollyController {
    private static String url;
    private static RequestQueue requestQueue;

    public static void init(Context context){
        if (requestQueue ==null){
            requestQueue  = Volley.newRequestQueue(context);
        }
        url = context.getResources().getString(R.string.backend_addresss);
    }

    static RequestQueue get(){
        return requestQueue;
    }
    static String getUrl(){
        return url;
    }



}
