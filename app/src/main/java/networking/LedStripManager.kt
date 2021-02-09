package networking

import android.content.res.Resources
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.example.ledcontroller.R


class LedStripManager {

    companion object{
        var lights: ArrayList<LedStrip> = ArrayList();

        fun init(resources: Resources){
            VollyController.get().add(
                    JsonArrayRequest(Request.Method.GET, VollyController.getUrl() +"lights", null, Response.Listener
                    { response ->
                        lights.clear()
                        for( i in 0 until response.length()) {
                        lights.add(
                                LedStrip(
                                        response[i].toString()
                                )
                        )
                    }
                    },null))
        //}

        }

        fun get_leds(): ArrayList<LedStrip> {
            return lights
        }
    }
}