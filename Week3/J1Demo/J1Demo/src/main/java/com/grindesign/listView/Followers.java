package com.grindesign.listView;

//Robert Warren
//Java 1
//Term 1405

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.grinddesign.Java1.j1demo.j1demo.MainActivity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;




public class Followers {
    //set context
    Context followerCon;
    //Set TAG constant
    static String TAG = "NETWORK DATA - Followers";
    //set classes context
    public Followers(Context context) {

        followerCon = context;
    }

    //test for connectivity
    public boolean follThis() {
        ConnectivityManager cm = (ConnectivityManager) followerCon.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        //verify connectivity exists
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {

            //logout network type
            Log.i(TAG, netInfo.getTypeName());

            //run code to get data from twitter
            new loadTwitterToken().execute();

            return true;

        }
        // alert user when there is no network connectivity
        else {

            //toast that there is no data and to check connection
            Toast.makeText(followerCon, "Please connect to a network to get information", Toast.LENGTH_LONG).show();

            return false;
        }


    }

    //set constants for OAUTH
    static final String twitterAPIKEY = "fQOl6VG5yKkqNegDJL6hz7NnX";

    static final String twitterAPISECRET = "H1LsT1XpV72lTkwZXuZebrI6uIw9FRW46jbMaPGg5ymwHTOGbC";

    static final String twitterAPIurl = "https://api.twitter.com/1.1/followers/list.json?screen_name=";

    static final String screenName = "grindnmosh";

    static String tweeterURL = twitterAPIurl + screenName;

    //set global variables
    String twitterToken;
    String jsonTokenStream;
    StringBuilder builder;

    //start ASync calls
    protected class loadTwitterToken extends AsyncTask<Void, Void, Integer> {

        //requires it to run in background
        @Override
        protected Integer doInBackground(Void... params) {
            //Log.i("test", "in BG");

            try {
                //establish connection
                DefaultHttpClient httpclient = new DefaultHttpClient(
                        new BasicHttpParams());
                //prepare to get authenticated on twitter OAUTH URL
                HttpPost httppost = new HttpPost(
                        "https://api.twitter.com/oauth2/token");

                //encode and submit key and secret
                String apiString = twitterAPIKEY + ":" + twitterAPISECRET;
                String authorization = "Basic "
                        + Base64.encodeToString(apiString.getBytes(),
                        Base64.NO_WRAP);

                //set up security to keep data hidden
                httppost.setHeader("Authorization", authorization);
                httppost.setHeader("Content-Type",
                        "application/x-www-form-urlencoded;charset=UTF-8");
                httppost.setEntity(new StringEntity(
                        "grant_type=client_credentials"));

                InputStream inputStream = null;

                //get response from twitter with the access token and convert it to string and assign to token variable
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity entity = response.getEntity();

                inputStream = entity.getContent();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(inputStream, "UTF-8"), 8);
                StringBuilder sb = new StringBuilder();

                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }

                jsonTokenStream = sb.toString();

                return 1;
            } catch (Exception e) {
                Log.e("loadTwitterToken",
                        "doInBackground Error:" + e.getMessage());
                return null;
            }
        }

        //process the token and instantiate the method to begin pulling the data
        @Override
        protected void onPostExecute(Integer result) {
            //Log.i("test", "in BG main 2");
            // Extract Token from JSON stream
            try {
                JSONObject root = new JSONObject(jsonTokenStream);
                twitterToken = root.getString("access_token");
            } catch (Exception e) {
                Log.e("loadTwitterToken", "onPost Error:" + e.getMessage());
            }

            // execute getting the data
            new loadTwitterFeed().execute();
        }
    }


    protected class loadTwitterFeed extends AsyncTask<Void, Void, JSONArray> {

        @Override
        protected JSONArray doInBackground(Void... params) {
            //Log.i("test", "in BG 2");

            //assign constants
            BufferedReader reader = null;
            builder = new StringBuilder();
            JSONArray feedsObject = new JSONArray();

            try {
                //recoonect with twitter this time to the url to pull the JSON data from
                DefaultHttpClient httpClient = new DefaultHttpClient(
                        new BasicHttpParams());

                //call the twitter JSON url we established earlier
                HttpGet httpget = new HttpGet(tweeterURL);

                // set up security
                httpget.setHeader("Authorization", "Bearer " + twitterToken);
                httpget.setHeader("Content-type", "application/json");

                //get response from twitter
                InputStream inputStream = null;
                HttpResponse response = httpClient.execute(httpget);
                HttpEntity entity = response.getEntity();

                //grab the data from the response
                inputStream = entity.getContent();
                reader = new BufferedReader(
                        new InputStreamReader(inputStream, "UTF-8"), 8);

                //pull the data from the buffer and build it into a string
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);

                }
                //Log.d("this is my array", "arr110: " + builder);

                //load object to object
                JSONObject feedObj = new JSONObject(builder.toString());
                //pull the array buried in the object that you need
                feedsObject = feedObj.getJSONArray("users");
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (reader != null)
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }


            //return array
            return feedsObject;

        }

        protected void onPostExecute(JSONArray result) {

            //Log.i("test", "enter a try");
            try {
                //loop through passed in array
               for (int t=0; t<result.length(); t++) {
                   //reset string builder each time
                    StringBuilder sb = new StringBuilder();
                    //Log.i("test", "enter a ray");
                   //grab the object from the current index in the array
                    JSONObject tweetObject = result.getJSONObject(t);
                    //Log.i("test", "enter a ray2");
                    //sb.append(tweetObject.getString("from_user")+": ");
                    try {
                        //grab the specific data you want
                        sb.append(tweetObject.getString("name"));
                        //give it a break
                        sb.append("\n");
                        //Log.i("test", "enter a ray3");
                    } catch (JSONException e) {
                        e.printStackTrace();
                        sb.append("any random text");
                    }
                   //assign the desired data to a string
                    String posting = sb.toString();
                   //add to the array for the adapter
                    MainActivity.testArray2.add(posting);
                    //Log.d("this is my array", "arr130: " + MainActivity.testArray3.toString());
                }
                //reset the adapter to load the new data
                MainActivity.mainListAdapter.notifyDataSetChanged();
            } catch (JSONException e) {
                Log.e("this is a JSON error follower", e.getMessage());
                e.printStackTrace();
            }


        }


    }

}
