package networking

import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject


class LedGroupManager {

    companion object {
        var lights: ArrayList<LedGroup> = ArrayList();

        fun init(after: () -> Unit) {
            VollyController.get().add(
                JsonArrayRequest(
                    Request.Method.GET,
                    VollyController.getUrl() + "led/group/",
                    null,
                    Response.Listener
                    { response ->
                        lights.clear()
                        for (i in 0 until response.length()) {
                            lights.add(LedGroup.parse(response[i] as JSONObject))
                        }
                        after.invoke()
                    },
                    null
                )
            )
        }
        fun kill_all(after: () -> Unit) {
            VollyController.get().add(
                JsonObjectRequest(
                    Request.Method.PUT,
                    VollyController.getUrl() + "led/off",
                    null,
                    { _ -> init(after)  },//This is not working for some reason.
                    { obj: VolleyError -> obj.printStackTrace() })
            )
        }

        fun get_leds(): ArrayList<LedGroup> {
            return lights
        }
    }
}