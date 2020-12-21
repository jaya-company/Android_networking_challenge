package com.example.android.networkconnect.Models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class Character {

    public Integer Id;
    public String Name;
    public String Description;
    public String ImageUrl;

    public static ArrayList<Character> ParseJson(String jsonData) {
        ArrayList<Character> resultSet = new ArrayList<>();

        try {
            final JSONObject obj = new JSONObject(jsonData);
            final JSONArray results = obj.getJSONArray("results");

            for (int i = 0; i < results.length(); i++) {
                JSONObject item = results.getJSONObject(i);

                Character characterItem = Character.ParseItem(item);
                resultSet.add(characterItem);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return resultSet;
    }

    private static Character ParseItem(JSONObject item) {
        Character result = new Character();
        try {
            result.Id = item.getInt("id");
            result.Name = item.getString("name");
            result.ImageUrl = item.getString("image");

            final String species = item.getString("species");
            final String gender = item.getString("gender");
            final String status = item.getString("status");
            result.Description = MessageFormat.format("Status: {0} | Gender: {1} | Species: {2}", status, gender, species);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }
}
