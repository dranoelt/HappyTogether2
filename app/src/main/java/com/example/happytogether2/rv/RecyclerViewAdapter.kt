package com.example.happytogether2.rv

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.happytogether2.R
import com.example.happytogether2.database.History
import org.w3c.dom.Text

class RecyclerViewAdapter(val items : List<History>) : RecyclerView.Adapter<itemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemViewHolder {
        return itemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_rv_item, parent, false))
    }

    override fun onBindViewHolder(holder: itemViewHolder, position: Int) {
        val history: History = items[position]
        holder.itemTitle.text = history.title
        holder.itemDate.text = history.tgl
        holder.itemDesc.text = history.desc
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

class itemViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    var itemTitle: TextView = itemView.findViewById(R.id.tv_title)
    var itemDate: TextView = itemView.findViewById(R.id.tv_date)
    var itemDesc: TextView = itemView.findViewById(R.id.tv_desc)
}

