package com.example.todolist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TacheAdapter (private val data: ArrayList<Tache>, private val context: Context) : RecyclerView.Adapter<TacheAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val nomTache : TextView = view.findViewById(R.id.nomTache)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tache_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.nomTache.text = item.nomTache

/*        context.addButton.setOnClickListener {

            data.add(Tache("New Tache"))
            notifyDataSetChanged()
            Toast.makeText(context, "Ajout!", Toast.LENGTH_LONG).show()
        }*/
    }

    override fun getItemCount(): Int {
        return data.size
    }
}