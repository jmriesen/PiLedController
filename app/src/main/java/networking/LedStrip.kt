package networking

import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest

/**
 * I want this to be a quick and convineate thing that the rest of the code can use.
 * If I end up changing away from volly I don't want the rest of the code to know.
 */
class LedStrip(name: String) {
    private val name: String = name

    fun power(on: Boolean) {
        val power = if (on) "on" else "off"
        VollyController.get().add(
                JsonObjectRequest(Request.Method.GET, VollyController.getUrl() + power + "/" + name, null, null, { obj: VolleyError -> obj.printStackTrace() }))
    }
    fun set(color : Int){
        VollyController.get().add(
            JsonObjectRequest(Request.Method.GET, VollyController.getUrl() + "set/" + name +"/"+color.toUInt(), null, null, { obj: VolleyError -> obj.printStackTrace() }))
    }

    fun getName(): String {
        return name
    }

    companion object {
        fun all_power(on: Boolean) {
            val power = if (on) "on" else "off"
            VollyController.get().add(
                    JsonObjectRequest(Request.Method.GET, VollyController.getUrl() + power, null, null, { obj: VolleyError -> obj.printStackTrace() }))
        }
    }

}