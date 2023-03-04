package com.example.todolist

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import java.util.prefs.Preferences

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private var data : ArrayList<Tache> = ArrayList<Tache>()
    private lateinit var adapter: TacheAdapter
    private lateinit var addButton: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addButton = findViewById(R.id.addButton)
        recyclerView = findViewById(R.id.recyclerTache)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = TacheAdapter(data,this)
        data = adapter.restoreTaches(this)
        adapter = TacheAdapter(data,this)
        recyclerView.adapter = adapter

       addButton.setOnClickListener {
            Tache.MY_VARIABLE++
            data.add(Tache("Bonjour"+Tache.MY_VARIABLE, false))
            adapter.notifyDataSetChanged()
            adapter.saveTaches(this, data)
        }
    }

    override fun onStop() {
        super.onStop()
        adapter.saveTaches(this, data)
    }

    override fun onDestroy() {
        super.onDestroy()
        adapter.saveTaches(this, data)
    }
}