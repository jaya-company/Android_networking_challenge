package com.example.android.networkconnect

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.networkconnect.model.Character

class CharactersAdapter(private var dataSet: List<Character>) :
        RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(android.R.id.text1)
        val image: ImageView = view.findViewById(android.R.id.icon)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
                .inflate(android.R.layout.activity_list_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.name.text = dataSet[position].name
        Glide.with(viewHolder.image)
                .load(dataSet[position].image)
                .centerCrop()
                .into(viewHolder.image)
    }

    override fun getItemCount() = dataSet.size

    fun addItems(dataSet: List<Character>) {
        this.dataSet = dataSet.toList()
        notifyDataSetChanged()
    }
}
