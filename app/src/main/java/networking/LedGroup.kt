package networking

import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import networking.patterns.*
import org.json.JSONObject

/**
 * I want this to be a quick and convineate thing that the rest of the code can use.
 * If I end up changing away from volly I don't want the rest of the code to know.
 */
class LedGroup(private val name: String, private var color: Pattern, private var on: Boolean) {

    fun power(on: Boolean) {
        this.on = on
        sendPower()
    }
    private fun sendPower(){
        val power = if(on) "on" else "off";
        VollyController.get().add(
            JsonObjectRequest(
                Request.Method.PUT,
                VollyController.getUrl() + "led/"+power+"/"+ name,
                null,
                null,
                { response_obj: VolleyError -> response_obj.printStackTrace() })
        )
    }
    fun set(color: Int){
        this.color = Constant(Instruction(color, Duration(0,0)))
        sendSet()
    }
    private fun sendSet(){
        val command = color
/*        val color_json = JSONObject()
        color_json.put("val", color)
        val obj = JSONObject()
        obj.put("Constant", color_json)
 */
        VollyController.get().add(
            JsonObjectRequest(
                Request.Method.PUT,
                VollyController.getUrl() + "led/group/" + name,
                command.json(),
                null,
                { response_obj: VolleyError -> response_obj.printStackTrace() })
        )
    }

    fun getName(): String {
        return name
    }
    fun getColor(): Int {
        return color.getColor()
    }
    fun getOn():Boolean{
        return on
    }

    companion object {

        fun parse(obj: JSONObject): LedGroup {
            val name = obj.get("name") as String
            val pattern = parsePattern (obj.get("pattern") as JSONObject)
            val on = obj.get("on") as Boolean
           return LedGroup(name,pattern,on)
        }
    }

}
