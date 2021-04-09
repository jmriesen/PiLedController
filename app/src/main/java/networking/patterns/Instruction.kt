package networking.patterns

import networking.LedGroup
import org.json.JSONObject


class Instruction(val color:Int,val fadeIn:Duration) {
    fun json(): JSONObject {
        val instruction = JSONObject()
        instruction.put("target", color)
        instruction.put("fade_time", fadeIn.json())
        return instruction
    }

    companion object {

        fun parse(obj: JSONObject): Instruction{
            val color = obj.get("target") as Int
            val duration = obj.get("fade_time") as JSONObject
            return Instruction(color, Duration.parse(duration))
        }
    }
}