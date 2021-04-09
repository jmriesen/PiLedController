package networking.patterns

import networking.LedGroup
import org.json.JSONObject

class Constant(private val instruction: Instruction):Pattern{
    override fun json(): JSONObject {
            val constant = JSONObject()
            constant.put("Constent", instruction.json())
            return constant
        }

    override fun getColor(): Int {
        return instruction.color
    }
    companion object {
        fun parse(obj: JSONObject): Constant {
            val instruction = Instruction.parse(obj.get("Constent") as JSONObject)
            return Constant(instruction)
        }
    }
}