package networking.patterns

import org.json.JSONObject

/**
 * The java duration class will not work for my phone as its version of android is two low.
 * But all I really need is a container as I am not interacting with the rest of the time ecosystem.
 * So I made this class
 */
class Duration (val seconds :Int,val nanos: Int){
    fun json(): JSONObject {
        val duration = JSONObject()
        duration.put("secs",seconds)
        duration.put("nanos",nanos)
        return duration
    }
    companion object{
    fun parse(obj: JSONObject): Duration{
        val seconds = obj.get("secs") as Int
        val nanos = obj.get("nanos")  as Int
        return Duration(seconds,nanos)
    }
    }
}