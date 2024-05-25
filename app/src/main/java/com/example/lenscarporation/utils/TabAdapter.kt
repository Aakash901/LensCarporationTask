package com.example.lenscarporation.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lenscarporation.R

class TabAdapter(
    private val tabNames: List<String>,
    private val onTabClick: (Int) -> Unit
) : RecyclerView.Adapter<TabAdapter.TabViewHolder>() {

    class TabViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tabName: TextView = itemView.findViewById(R.id.tab_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TabViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tab, parent, false)
        return TabViewHolder(view)
    }

    override fun onBindViewHolder(holder: TabViewHolder, position: Int) {
        holder.tabName.text = tabNames[position]
        holder.itemView.setOnClickListener {
            onTabClick(position)
        }
    }

    override fun getItemCount(): Int = tabNames.size
}
