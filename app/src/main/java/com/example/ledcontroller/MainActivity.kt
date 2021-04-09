package com.example.ledcontroller

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import networking.LedGroupManager
import networking.VollyController

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        VollyController.init(this)
        recyclerView = findViewById(R.id.recyclerView)

        val myAdapter = LightsListView(this, LedGroupManager.lights)
        recyclerView.adapter = myAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)



        LedGroupManager.init { myAdapter.notifyDataSetChanged(); }

        findViewById<Button>(R.id.update).setOnClickListener { _-> LedGroupManager.init { myAdapter.notifyDataSetChanged(); } }
        findViewById<Button>(R.id.all_off).setOnClickListener { _ -> LedGroupManager.kill_all { myAdapter.notifyDataSetChanged(); } }
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
}
