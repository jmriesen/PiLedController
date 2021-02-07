package com.example.ledcontroller

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Switch
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException

class MainActivity : AppCompatActivity() {
    lateinit var lights : Array<String>
    lateinit var recyclerView : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        VollyController.init(this)

        lights = resources.getStringArray(R.array.lights)
        recyclerView = findViewById(R.id.recyclerView)

        val myAdapter = MyAdapter(this,lights)
        recyclerView.adapter = myAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
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