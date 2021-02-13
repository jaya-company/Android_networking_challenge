package com.example.android.networkconnect.RickMortyAPI.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.networkconnect.R
import com.squareup.picasso.Picasso

class CharactersAdapter(val context: Context, private var content: List<Character>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.characters_adapter, parent, false)

        return ProviderViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return content.count()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (content.getOrNull(position) == null) {
            return
        }

        val currentModel: Character = content[position]
        val currentHolder = holder as ProviderViewHolder

        Picasso
                .get()
                .load(currentModel.image)
                .fit()
                .centerCrop()
                .placeholder(R.drawable.progress_animation)
                .into(currentHolder.image)

        currentHolder.name.text =  currentModel.name
        currentHolder.gender.text = currentModel.gender
    }

    class ProviderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.nameTextView)
        var image: ImageView = itemView.findViewById(R.id.characterImage)
        var gender: TextView = itemView.findViewById(R.id.genderTextView)
    }
}