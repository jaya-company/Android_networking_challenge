package com.example.android.networkconnect.Adapters;
import java.util.ArrayList;
import java.util.List;
import com.example.android.networkconnect.*;
import com.example.android.networkconnect.Models.Character;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class CharactersListAdapter extends RecyclerView.Adapter<CharactersListAdapter.ViewHolder> {
    private List<Character> values;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public TextView descriptionTextView;
        public ImageView iconImageView;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            iconImageView =  (ImageView) v.findViewById(R.id.item_icon);
            nameTextView = (TextView) v.findViewById(R.id.item_name);
            descriptionTextView = (TextView) v.findViewById(R.id.item_description);
        }
    }

    public void add(int position, Character item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    public CharactersListAdapter(List<Character> data) {
        values = data;
    }

    public CharactersListAdapter() {
        values = new ArrayList<Character>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Character item = values.get(position);
        holder.nameTextView.setText(item.Name);
        holder.descriptionTextView.setText(item.Description);

        ImageLoader.getInstance().displayImage(item.ImageUrl, holder.iconImageView);
    }

    @Override
    public int getItemCount() {
        return values.size();
    }
}
