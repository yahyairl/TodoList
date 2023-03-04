package com.example.todolist

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TacheAdapter (private var data: ArrayList<Tache>, private val context: Context) : RecyclerView.Adapter<TacheAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val nomTache : EditText = view.findViewById(R.id.nomTache)
        val fermerTache : ImageButton = view.findViewById(R.id.fermerTache)
        val checkBox : CheckBox = view.findViewById(R.id.checkBox)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tache_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = data[position]
        // on défini le texte de l'EditView comme etant le nom de la tache item
        holder.nomTache.setText(item.nomTache)

        // nomTache c'est le nom de mon EditText
        holder.nomTache.addTextChangedListener {
            var nouveauNom = it.toString()
            if (holder.adapterPosition != RecyclerView.NO_POSITION) {
                data[holder.adapterPosition].nomTache = nouveauNom
                saveTaches(context, data)
            }
        }

        holder.checkBox.setOnClickListener {
            if(item.isFinished){
                item.isFinished = false
            } else {
                item.isFinished = true
            }
            notifyDataSetChanged()
        }
        holder.checkBox.isChecked = item.isFinished

        holder.fermerTache.setOnClickListener {
            data.removeAt(position)
            notifyDataSetChanged()
            saveTaches(context, data)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }


    fun saveTaches(context: Context, listTache: List<Tache>) {
        val sharedPreferences = context.getSharedPreferences("listeTache", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        editor.putString("taches", gson.toJson(listTache))
        editor.apply()
    }

    fun restoreTaches(context: Context) : ArrayList<Tache> {
        val sharedPreferences = context.getSharedPreferences("listeTache", Context.MODE_PRIVATE)
        val gson = Gson()
        val tachesJson = sharedPreferences.getString("taches", null)
        val type = object : TypeToken<ArrayList<Tache>>() {}.type
        return gson.fromJson(tachesJson, type)
    }
}