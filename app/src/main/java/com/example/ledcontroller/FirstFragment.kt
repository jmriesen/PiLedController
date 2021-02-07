package com.example.ledcontroller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Switch
import androidx.navigation.fragment.findNavController
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            val request = JsonObjectRequest(Request.Method.GET, VollyController.getUrl() + "on", null, null, Response.ErrorListener { error -> error.printStackTrace() })
            VollyController.get().add(request)
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }
    fun switch_click_handle(view: View) {
        if ((view as Switch).isChecked) {
            val request = JsonObjectRequest(Request.Method.GET, VollyController.getUrl() + "on", null, null, Response.ErrorListener { error -> error.printStackTrace() })
            VollyController.get().add(request)
        } else {
            val request = JsonObjectRequest(Request.Method.GET, VollyController.getUrl() + "off", null, null, Response.ErrorListener { error -> error.printStackTrace() })
            VollyController.get().add(request)
        }
    }

}