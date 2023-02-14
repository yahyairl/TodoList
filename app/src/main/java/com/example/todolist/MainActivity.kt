package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private var data : ArrayList<Tache> = ArrayList<Tache>()
    private lateinit var adapter: TacheAdapter
    private lateinit var addButton: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        data.add(Tache("tache1"))
        data.add(Tache("tache2"))
        data.add(Tache("tache3"))
        data.add(Tache("tache4"))

        addButton = findViewById(R.id.addButton)
        recyclerView = findViewById(R.id.recyclerTache)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = TacheAdapter(data,this)
        recyclerView.adapter = adapter

        addButton.setOnClickListener {
            data.add(Tache("new tache"))
            adapter.notifyDataSetChanged()
            Toast.makeText(this@MainActivity, "Ajout!", Toast.LENGTH_LONG).show()
        }


    }

}