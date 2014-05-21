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

import com.grindesign.listView.FeedMe;
import com.grindesign.listView.Followers;
import com.grindesign.listView.Friends;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    //declare gloabal variables
    Context twitCon;
    String[] choiceItems;
    String[] followerItems;
    String[] followingItems;
    //final TextView tv = (TextView) findViewById(R.id.tweeter);
    public static ArrayList<String> testArray;
    public static ArrayList<String> testArray2;
    public static ArrayList<String> testArray3;
    ArrayAdapter<String> mainListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //call layout
        setContentView(R.layout.activity_main);
        final FeedMe fm = new FeedMe(this);
        final Friends friends = new Friends(this);
        final Followers foll = new Followers(this);



        //initializing constants
        Spinner s = (Spinner) findViewById(R.id.choices);
        final View tb = findViewById(R.id.tweetBox);
        final ListView lv = (ListView) findViewById(R.id.mainList);
        final Button sub = (Button) findViewById(R.id.submit);
        final EditText edit = (EditText) findViewById(R.id.twitText);

        testArray = new ArrayList<String>();
        testArray2 = new ArrayList<String>();
        testArray3 = new ArrayList<String>();

        twitCon = this;
        choiceItems = getResources().getStringArray(R.array.choices_array);
        followerItems = getResources().getStringArray(R.array.sampleF1_array);
        followingItems = getResources().getStringArray(R.array.sampleF2_array);

        //spinner adapter
        ArrayAdapter<String> choicesAdapter = new ArrayAdapter<String>(twitCon, android.R.layout.simple_spinner_item, choiceItems);


        //use UI Spinner and load it
        s.setAdapter(choicesAdapter);


        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (choiceItems[position].equals("Feed")) {

                    fm.twitThis();

                    //determines visibilty of new tweet option to only be available on Feed //added Thursday morning because it bothered me that it was there even when not necessary
                    tb.setVisibility(view.GONE);


                    //create your new arraylist
                    List<String> eatery = new ArrayList<String>();

                    //add all the array items from the class to the new list
                    eatery.addAll(testArray);


                    //create adapter calling on the dynamic array from FeedMe Class // this will be dynamic data in week 3 from the API
                    mainListAdapter = new ArrayAdapter<String>(twitCon, android.R.layout.simple_list_item_1, eatery);

                    //load adapter into listview
                    lv.setAdapter(mainListAdapter);

                    //populate toasty for the selected spinner item
                    //fm.Toasty("Just Here To " + choiceItems[position]);


                } else if (choiceItems[position].equals("Followers")) {

                    friends.friendThis();


                    //determines visibilty of new tweet option to only be available on Feed //added Thursday morning because it bothered me that it was there even when not necessary
                    tb.setVisibility(view.GONE);

                    //create adapter to load static string array into list view // this will be dynamic data in week 3 from the API
                    mainListAdapter = new ArrayAdapter<String>(twitCon, android.R.layout.simple_list_item_1, testArray2);

                    //load adapter into listview
                    lv.setAdapter(mainListAdapter);

                    //populate toasty for the selected spinner item
                    //fm.Toasty("Here's Your " + choiceItems[position]);

                } else if (choiceItems[position].equals("Following")) {

                    foll.follThis();

                    //determines visibilty of new tweet option to only be available on Feed //added Thursday morning because it bothered me that it was there even when not necessary
                    tb.setVisibility(view.GONE);

                    //create adapter to load static string array into list view // this will be dynamic data in week 3 from the API
                    mainListAdapter = new ArrayAdapter<String>(twitCon, android.R.layout.simple_list_item_1, testArray3);

                    //load adapter into listview
                    lv.setAdapter(mainListAdapter);

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


