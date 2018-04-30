package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        Log.d("sandwich", json);

        if (json == null || json.isEmpty()) {
            return null;
        }
        JSONObject jsonObject;
        Sandwich sandwich = new Sandwich();

        try {
            jsonObject = new JSONObject(json);
            ArrayList<String> list;

            // Get name's nested structure
            JSONObject nameJson = new JSONObject(jsonObject.getString("name"));

            // Copy mainName from JSON
            sandwich.setMainName(nameJson.getString("mainName"));

            // alsoKnownAs is a JSON Array and Sandwich takes a list of Strings
            String aka = nameJson.getString("alsoKnownAs");
            JSONArray jsonArray = new JSONArray(aka);
            Log.d("Sandwich:jsonArray", jsonArray.toString());
            if (jsonArray != null) {
                list = jsonArrayToArrayList(jsonArray);
                sandwich.setAlsoKnownAs(list);
            }

            // Copy the place of origin
            String place = jsonObject.getString("placeOfOrigin");
            if (place != null && !place.isEmpty()) {
                sandwich.setPlaceOfOrigin(place);
            }

            // Copy the description
            String description = jsonObject.getString("description");
            if (description != null && !description.isEmpty()) {
                sandwich.setDescription(description);
            }

            // Copy the image url
            String image = jsonObject.getString("image");
            if (image != null && !image.isEmpty()) {
                sandwich.setImage(image);
            }

            // ingredients is also a JSON Array and Sandwich takes a list of strings
            String ingredients = jsonObject.getString("ingredients");
            jsonArray = new JSONArray(ingredients);
            if (jsonArray != null) {
                list = jsonArrayToArrayList(jsonArray);
                sandwich.setIngredients(list);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sandwich;
    }

    /**
     * jsonArrayToArrayList() takes a JSONArray of string and returns an ArrayList of Strings
     * @param jsonArray a JSON array of strings
     * @return null if jsonArray is null or an ArrayList of Strings
     */
    public static ArrayList<String> jsonArrayToArrayList(JSONArray jsonArray) {
        if (jsonArray == null) {
            return null;
        }

        ArrayList<String> list = new ArrayList<String>();

        // iterate through json array elements and add to list
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                list.add(jsonArray.getString(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return list;
    }
}
