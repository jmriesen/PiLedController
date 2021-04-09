package networking.patterns

import org.json.JSONObject
import java.lang.Exception

interface Pattern {
    fun json():JSONObject
    fun getColor():Int
}
fun parsePattern(obj:JSONObject):Pattern{
    var pattern :Pattern
    pattern = Constant.parse(obj)
    return pattern
}