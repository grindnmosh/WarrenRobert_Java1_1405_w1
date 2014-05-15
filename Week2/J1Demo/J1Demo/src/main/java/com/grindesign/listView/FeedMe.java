package com.grindesign.listView;

//Robert Warren
//Java 1
//Term 1405

import android.content.Context;
import android.util.Log;
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
            //Log.i("object output ", "build log");
            enumFile ef3 = new enumFile();

            // Create Feeds Object fo query
            for (enumFile.Feeds feed : enumFile.Feeds.values()) {
                //Log.i("object output ", "build inside loop log");
                // create post object
                JSONObject postingObject = new JSONObject();

                //load postingObject
                postingObject.put("posting", feed.setPosting());
                postingObject.put("dated", feed.setDated());
                queryTheFeed.put(postingObject);

            }

            //add the query to the feedsobject
            feedsObject.put("query", queryTheFeed);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return feedsObject;

    }

    public ArrayList loadArray() {
        Log.i("object output ", "LoadArray Hit");
        String post, when;
        String result = null;




        ArrayList<String> posts = new ArrayList<String>();


        try {

            JSONObject readObject = buildJSON();
            Log.i("object output ", "enter the try");
            JSONArray JA = readObject.getJSONArray("query");
            Log.i("object output ", "length test" + JA.length());
            for (int i = 0; i < JA.length(); i++) {

                Log.i("object output ", "test log");
                JSONObject JO = JA.getJSONObject(i);
                String posting = JO.getString("posting");
                String dated = JO.getString("dated");
                Log.i("object output ", posting);
                Log.i("object output ", dated);
                posts.add(posting);





            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return posts;

    }

}
