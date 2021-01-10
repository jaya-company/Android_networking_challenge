package com.example.android.networkconnect.adapters

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.networkconnect.R
import com.example.android.networkconnect.models.Character
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.character_item_row.view.*

/**
 * Adapter to the shop items
 */
class CharactersAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    /**
     * Reference to the last query search
     */
    private var characters: List<Character>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
                LayoutInflater.from(parent.context).inflate(R.layout.character_item_row, parent, false)
        return CharacterItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val vh = holder as CharacterItemViewHolder
        characters?.get(position)?.let { vh.bind(it) }
    }

    /**
     * Sets the QuerySearch object and sets the items needed
     */
    fun setCharacters(characters: List<Character>) {
        this.characters = characters
        notifyDataSetChanged()
    }

    /**
     * Clears the QuerySearch and the adapter
     */
    fun clearCharacters() {
        this.characters = null
        notifyDataSetChanged()
    }

    /**
     * ViewHolder for the items
     */
    inner class CharacterItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        /**
         * Binds the item
         */
        fun bind(item: Character) = with(itemView) {
            titleTextView.text = item.name

            speciesTextView.text = item.species

            if (!TextUtils.isEmpty(item.image)) {
                Picasso.get().load(item.image).into(thumbnailImageView)
            }
        }
    }

    override fun getItemCount(): Int {
        return characters?.size ?: run { 0 }
    }

}