package com.grindesign.listView;

//Robert Warren
//Java 1
//Term 1405

import android.content.Context;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class FeedMe {

    Context feedCon;

    public FeedMe(Context context) {
        feedCon = context;
    }

    public void Toasty(String str) {
        Toast.makeText(feedCon, str, Toast.LENGTH_LONG).show();
    }

    public JSONObject buildJSON() {


        //create feed JSON Object
        JSONObject feedsObject = new JSONObject();

        try {
            // create query JSON Object
            JSONArray queryTheFeed = new JSONArray();

            // Create Feeds Object fo query
            for (enumFile.Feeds feed : enumFile.Feeds.values()) {
                //Log.i("object output ", "build inside loop log");

                // create post object
                JSONObject postingObject = new JSONObject();

                //load postingObject
                postingObject.put("posting", feed.setPosting());
                postingObject.put("dated", feed.setDated());

                //load int the JSONArray
                queryTheFeed.put(postingObject);

            }

            //add the query to the feedsobject
            feedsObject.put("query", queryTheFeed);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        //return the JSON Object
        return feedsObject;

    }

    // create the array for the adapter
    public ArrayList loadArray() {
        //Log.i("object output ", "LoadArray Hit");

        //instatiate the array to be passed
        ArrayList<String> posts = new ArrayList<String>();


        try {
            //variable to pass the JSON Object
            JSONObject readObject = buildJSON();
            //Log.i("object output ", "enter the try");

            //capture the JSON Array from the JSON Object
            JSONArray JA = readObject.getJSONArray("query");
            //Log.i("object output ", "length test" + JA.length());

            //Loop through the JSON Array to load an Array List
            for (int i = 0; i < JA.length(); i++) {
                //Log.i("object output ", "test log");

                //grab JSON Object from array and current index location
                JSONObject JO = JA.getJSONObject(i);

                //grab the items out of each object for later use
                String posting = JO.getString("posting");
                String dated = JO.getString("dated"); //this item is not used for this week
                //Log.i("object output ", posting);
                //Log.i("object output ", dated);

                //add the posting item to Arraylist for use in Array Adapter for List View
                posts.add(posting);





            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Return the Array for use in Main Activity to call on it
        return posts;

    }

}
