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
import java.util.ArrayList;


public class FeedMe {


    Context feedCon;
    static String TAG = "NETWORK DATA - FeedMe";
    public FeedMe(Context context) {
        feedCon = context;
    }

    public void Toasty(String str) {
        Toast.makeText(feedCon, str, Toast.LENGTH_LONG).show();
    }

    public boolean twitThis() {
        ConnectivityManager cm = (ConnectivityManager) feedCon.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            new loadTwitterToken().execute();
            Log.i(TAG, netInfo.getTypeName());
            return true;

        }
        else {

            Toast.makeText(feedCon, "Please connect to a network to get information", Toast.LENGTH_LONG).show();
            //else toast that there is no data and to check connection
            return false;
            }


    }

    static final String twitterAPIKEY = "fQOl6VG5yKkqNegDJL6hz7NnX";

    static final String twitterAPISECRET = "H1LsT1XpV72lTkwZXuZebrI6uIw9FRW46jbMaPGg5ymwHTOGbC";

    static final String twitterAPIurl = "https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name=";

    static final String screenName = "grindnmosh";

    static final int tweets2Return = 20;


    static String tweeterURL = twitterAPIurl + screenName
            + "&include_rts=1&count=" + tweets2Return;

    String twitterToken;
    String jsonTokenStream;
    JSONArray jsonFeed;
    String[] tweetJSON;
    ArrayList<String> posts;
    StringBuilder builder;
    ArrayList list = null;

    //ASync
    protected class loadTwitterToken extends AsyncTask<Void, Void, Integer> {

        @Override
        protected Integer doInBackground(Void... params) {
            //Log.i("test", "in BG");

            try {
                DefaultHttpClient httpclient = new DefaultHttpClient(
                        new BasicHttpParams());
                HttpPost httppost = new HttpPost(
                        "https://api.twitter.com/oauth2/token");

                String apiString = twitterAPIKEY + ":" + twitterAPISECRET;
                String authorization = "Basic "
                        + Base64.encodeToString(apiString.getBytes(),
                        Base64.NO_WRAP);

                httppost.setHeader("Authorization", authorization);
                httppost.setHeader("Content-Type",
                        "application/x-www-form-urlencoded;charset=UTF-8");
                httppost.setEntity(new StringEntity(
                        "grant_type=client_credentials"));

                InputStream inputStream = null;

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

            new loadTwitterFeed().execute();
        }
    }


    protected class loadTwitterFeed extends AsyncTask<Void, Void, JSONArray> {

        @Override
        protected JSONArray doInBackground(Void... params) {
            //Log.i("test", "in BG 2");
            BufferedReader reader = null;
            builder = new StringBuilder();
            JSONArray feedsObject = new JSONArray();
            try {
                DefaultHttpClient httpClient = new DefaultHttpClient(
                        new BasicHttpParams());
                HttpGet httpget = new HttpGet(tweeterURL);
                httpget.setHeader("Authorization", "Bearer " + twitterToken);
                httpget.setHeader("Content-type", "application/json");

                InputStream inputStream = null;
                HttpResponse response = httpClient.execute(httpget);
                HttpEntity entity = response.getEntity();

                inputStream = entity.getContent();
                reader = new BufferedReader(
                        new InputStreamReader(inputStream, "UTF-8"), 8);
                String line;

                while ((line = reader.readLine()) != null) {
                    builder.append(line);

                }
                //Log.d("this is my array", "arr: " + builder);
                //feedsObject.put("query", builder);
                feedsObject = new JSONArray(builder.toString());
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


            return feedsObject;

        }

        protected void onPostExecute(JSONArray result) {

            //Log.i("test", "enter a try");
            posts = new ArrayList<String>();
            //JSONObject tweetArray =  result.getJSONObject(0);
            //Log.d("this is my array", "arr30: " + result.toString());
            try {
                for (int t=0; t<result.length(); t++) {
                    StringBuilder sb = new StringBuilder();
                    //Log.i("test", "enter a ray");
                    JSONObject tweetObject = result.getJSONObject(t);
                    //Log.i("test", "enter a ray2");
                    //sb.append(tweetObject.getString("from_user")+": ");
                    try {
                        sb.append(tweetObject.getString("text"));
                        sb.append("\n");
                        //Log.i("test", "enter a ray3");
                    } catch (JSONException e) {
                        e.printStackTrace();
                        sb.append("any random text");
                    }
                    String posting = sb.toString();
                    MainActivity.testArray.add(posting);
                    //Log.d("this is my array", "arr45: " + MainActivity.testArray.toString());
                }
            } catch (JSONException e) {
                Log.e("this is a JSON error", e.getMessage());
                e.printStackTrace();
            }


        }


    }





}

