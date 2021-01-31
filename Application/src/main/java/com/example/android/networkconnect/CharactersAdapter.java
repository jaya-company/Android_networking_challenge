package com.example.android.networkconnect;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.networkconnect.model.Character;
import com.example.android.networkconnect.model.CharacterResult;
import com.squareup.picasso.Picasso;

class CharactersAdapter extends RecyclerView.Adapter<CharactersAdapter.CharactersHolder> {

    private CharacterResult characters;

    public CharactersAdapter(CharacterResult characters) {
        this.characters = characters;
    }

    @NonNull
    @Override
    public CharactersHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.character_layout, viewGroup, false);
        return new CharactersHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CharactersHolder charactersHolder, int position) {
        Character character = characters.results.get(position);

        Picasso.get().load(character.image).into(charactersHolder.characterImage);
        charactersHolder.characterName.setText(character.name);
        charactersHolder.characterSpecies.setText(character.species);
        charactersHolder.characterType.setText(character.type);
        charactersHolder.characterGender.setText(character.gender);
    }

    @Override
    public int getItemCount() {
        return characters.results.size();
    }

    public static class CharactersHolder extends RecyclerView.ViewHolder {
        final ImageView characterImage;
        final TextView characterName;
        final TextView characterSpecies;
        final TextView characterType;
        final TextView characterGender;


        public CharactersHolder(@NonNull View itemView) {
            super(itemView);

            characterImage = itemView.findViewById(R.id.characterImage);
            characterName = itemView.findViewById(R.id.characterName);
            characterSpecies = itemView.findViewById(R.id.characterSpecies);
            characterType = itemView.findViewById(R.id.characterType);
            characterGender = itemView.findViewById(R.id.characterGender);
        }
    }
}
