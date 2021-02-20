package com.example.android.networkconnect.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.android.networkconnect.R
import com.example.android.networkconnect.domain.Character

class CharacterAdapter: RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {
    private val characterList = ArrayList<Character>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false))
    }

    override fun getItemCount(): Int = characterList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = characterList[position]

        holder.tvSpeakerName.text = character.name
        Glide
                .with(holder.itemView.context)
                .load(character.image)
                .apply(RequestOptions.circleCropTransform())
                .into(holder.ivSpeakerImage)

    }

    fun updateData(data: List<Character>) {
        characterList.clear()
        characterList.addAll(data)
        notifyDataSetChanged()
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvSpeakerName : TextView = itemView.findViewById(R.id.tvCharacterName)
        val ivSpeakerImage : ImageView = itemView.findViewById(R.id.ivCharacterImage)
    }
}
