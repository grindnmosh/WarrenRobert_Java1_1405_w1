package com.grindesign.listView;

//Robert Warren
//Java 1
//Term 1405

import android.content.Context;
import android.widget.Toast;
import org.json.JSONObject;


/**
 * Created by 1LoveCyn on 5/13/14.
 */


public class FeedMe {
        //feed1("eating cereal", "5/11/14 05:10 PM"),
        //feed2("going to gym", "5/11/14 07:30 PM")



Context feedCon;

    public FeedMe(Context context) {
        feedCon = context;
    }

    public void Toasty(String str){
        Toast.makeText(feedCon, str, Toast.LENGTH_LONG).show();
    }

    public static JSONObject buildJSON() {

        //create feed JSON Object
        JSONObject feedsObject = new JSONObject();

        // create query JSON Object
        JSONObject queryTheFeed = new JSONObject();

        //cycle through enums with for loop... need help with enums

        return null;
    }

    public static String readJSON (String selected) {



        return null;
    }


}
