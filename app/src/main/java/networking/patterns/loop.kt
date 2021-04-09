package networking.patterns

import com.android.volley.toolbox.JsonArrayRequest
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

class loop(val instructions :Vector<Pair<Duration,Instruction>>) :Pattern{

    override fun json(): JSONObject {
        val instructionArray = JSONArray()
        for (pair in instructions){
            val timeAndInstruction = JSONArray()
            timeAndInstruction.put(pair.first.json())
            timeAndInstruction.put(pair.second.json())
            instructionArray.put(timeAndInstruction)
        }
        val instructionAndIndex = JSONArray()
        instructionAndIndex.put(0)
        instructionAndIndex.put(instructionArray)
        val loop = JSONObject()
        loop.put("Loop",instructionAndIndex)
        return loop
    }

    override fun getColor(): Int {
       return instructions[0].second.color
    }
}