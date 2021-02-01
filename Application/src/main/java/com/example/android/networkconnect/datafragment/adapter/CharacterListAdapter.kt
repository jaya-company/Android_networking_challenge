package com.example.android.networkconnect.datafragment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.networkconnect.R
import com.example.android.networkconnect.datafragment.model.Character
import com.example.android.networkconnect.util.getProgressDrawable
import com.example.android.networkconnect.util.loadImage
import kotlinx.android.synthetic.main.item_character.view.*

class CharacterListAdapter(var characters: ArrayList<Character>): RecyclerView.Adapter<
        CharacterListAdapter.CharacterViewHolder>() {
    fun updateCharacters(newCharacters: List<Character>) {
        characters.clear()
        characters.addAll(newCharacters)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = CharacterViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
    )

    override fun getItemCount() = characters.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(characters[position])
    }

    class CharacterViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val imageView = view.imageView
        private val characterName = view.name
        private val characterSpecies = view.species
        private val progressDrawable = getProgressDrawable(view.context)

        fun bind(character: Character) {
            characterName.text = character.name
            characterSpecies.text = character.species
            imageView.loadImage(character.image, progressDrawable)
        }
    }
}