//Robert Warren
//Java 1
//Term 1405

package com.grinddesign.Java1.j1demo.j1demo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.grinddesign.test.Connection;
import com.grindesign.listView.FeedMe;
import com.grindesign.listView.Followers;
import com.grindesign.listView.Friends;

import java.util.ArrayList;

public class MainActivity extends Activity {
    //declare gloabal variables
    Context twitCon;
    String[] choiceItems;

    //final TextView tv = (TextView) findViewById(R.id.tweeter);
    public static ArrayList<String> testArray;
    public static ArrayList<String> testArray2;
    public static ArrayList<String> testArray3;
    public static ArrayList<String> image;
    public static ArrayList<String> image2;
    public static ArrayList<String> image3;

    public static ArrayAdapter<String> mainListAdapter;
    public static CellAdapter postAdapter;
    public static FCellAdapter postAdapter2;
    public static FCellAdapter2 postAdapter3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //call layout
        setContentView(R.layout.activity_main);


        // instantiate classes to call within the app as needed
        final FeedMe fm = new FeedMe(this);
        final Friends friends = new Friends(this);
        final Followers foll = new Followers(this);

        Connection con = new Connection(this);
        con.connection();



        //initializing constants
        Spinner s = (Spinner) findViewById(R.id.choices);
        final View tb = findViewById(R.id.tweetBox);
        final ListView lv = (ListView) findViewById(R.id.mainList);
        final Button sub = (Button) findViewById(R.id.submit);
        final EditText edit = (EditText) findViewById(R.id.twitText);

        // instatiating arrays to assign the parsed data to
        testArray = new ArrayList<String>();
        testArray2 = new ArrayList<String>();
        testArray3 = new ArrayList<String>();
        image = new ArrayList<String>();
        image2 = new ArrayList<String>();
        image3 = new ArrayList<String>();

        // setup context
        twitCon = this;

        //assign labels to spinner array
        choiceItems = getResources().getStringArray(R.array.choices_array);

        //spinner adapter
        ArrayAdapter<String> choicesAdapter = new ArrayAdapter<String>(twitCon, android.R.layout.simple_spinner_item, choiceItems);


        //use UI Spinner and load it
        s.setAdapter(choicesAdapter);

        // set up on select methods to load data based on selected spinner item
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (choiceItems[position].equals("Feed")) {

                    //call  timeline from grindnmosh's user feed
                    fm.twitThis();

                    //determines visibilty of new tweet option to only be available on Feed //added Thursday morning because it bothered me that it was there even when not necessary
                    tb.setVisibility(view.GONE);

                    //create adapter calling on the dynamic array from FeedMe Class // this will be dynamic data in week 3 from the API
                    //mainListAdapter = new ArrayAdapter<String>(twitCon, android.R.layout.simple_list_item_1, testArray);
                    postAdapter = new CellAdapter(twitCon, R.layout.item_cell, testArray);


                    //load adapter into listview
                    lv.setAdapter(postAdapter);

                    //populate toasty for the selected spinner item
                    //fm.Toasty("Just Here To " + choiceItems[position]);


                } else if (choiceItems[position].equals("Followers")) {

                    //call  followers from grindnmosh's twitter account
                    foll.follThis();

                    //determines visibilty of new tweet option to only be available on Feed //added Thursday morning because it bothered me that it was there even when not necessary
                    tb.setVisibility(view.GONE);

                    //create adapter to load static string array into list view // this will be dynamic data in week 3 from the API
                    postAdapter2 = new FCellAdapter(twitCon, R.layout.item_fcell, testArray2);

                    //load adapter into listview
                    lv.setAdapter(postAdapter2);

                    //populate toasty for the selected spinner item
                    //fm.Toasty("Here's Your " + choiceItems[position]);

                } else if (choiceItems[position].equals("Following")) {

                    //call  friends from grindnmosh's twitter account
                    friends.friendThis();

                    //determines visibilty of new tweet option to only be available on Feed //added Thursday morning because it bothered me that it was there even when not necessary
                    tb.setVisibility(view.GONE);

                    //create adapter to load static string array into list view // this will be dynamic data in week 3 from the API
                    postAdapter3 = new FCellAdapter2(twitCon, R.layout.item_fcell, testArray3);

                    //load adapter into listview
                    lv.setAdapter(postAdapter3);

                    //populate toasty for the selected spinner item
                    //fm.Toasty("Who You Are " + choiceItems[position]);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //do nothing at this time
            }
        });
        //temporarily hiding till login auth can be implemented
        //on click for submit button // this button will be used to submit user dat back to the server in week 3
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //if no entry was made and button is clicked this will run
                if (edit.getText().length() == 0) {

                    //missing data error
                    fm.Toasty("Please enter a tweet");

                }
                //if user has entered in information
                else {

                    //success message
                    fm.Toasty("Your Tweet would have been submitted if this were week 3");

                    //resets data to a blank field
                    edit.setText("");

                    //reinsert the hint into the text field
                    edit.setHint(R.string.enter_tweet);
                }

            }

        });
    }
}


