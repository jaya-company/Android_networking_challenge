package com.example.android.networkconnect.ui;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.networkconnect.R;
import com.example.android.networkconnect.data.RmCharacter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RickMortyListAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<RmCharacter> itemList;

    public RickMortyListAdapter(Activity activity, ArrayList<RmCharacter> itemList) {
        super();
        this.activity = activity;
        this.itemList = itemList;
    }

    @Override
    public int getCount() {
        int count = 0;

        if (itemList.size() > 0) {
            count = itemList.size();
        }

        return count;
    }

    @Override
    public Object getItem(int i) {
        return itemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ItemRow row = new ItemRow();
        LayoutInflater inflater = activity.getLayoutInflater();

        if (view == null) {
            view = inflater.inflate(R.layout.rm_list_item, viewGroup, false);
            row.imageChar = (ImageView) view.findViewById(R.id.imageAvatar);
            row.nameChar = (TextView) view.findViewById(R.id.nameAvatar);

            view.setTag(row);

        } else {
            row = (ItemRow) view.getTag();
        }

        RmCharacter item = itemList.get(i);

        if (item != null) {
            Picasso.get()
                    .load(item.getImageUrl())
                    .resize(60,60)
                    .centerCrop()
                    .into(row.imageChar);
            row.nameChar.setText(item.getName());
        }

        return view;
    }

    public static class ItemRow {
        ImageView imageChar;
        TextView nameChar;
    }
}
