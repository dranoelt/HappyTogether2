package com.example.happytogether2.rv

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.happytogether2.R

class RecyclerViewAdapter(val items : ArrayList<String>, val context: Context) : RecyclerView.Adapter<itemViewHolder>() {
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: itemViewHolder, position: Int) {
        holder.itemTextView.text = items.get(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemViewHolder {
        return itemViewHolder(LayoutInflater.from(context).inflate(R.layout.view_rv_item, parent, false))
    }
}

class itemViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    var itemTextView: TextView = view.findViewById(R.id.tv_title)
}

