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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    //declare gloabal variables
    Context twitCon;
    String[] choiceItems;
    String[] followerItems;
    String[] followingItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //call layout
        setContentView(R.layout.activity_main);

        //initializing constants
        Spinner s = (Spinner) findViewById(R.id.choices);
        final ListView lv = (ListView) findViewById(R.id.mainList);
        final Button sub = (Button) findViewById(R.id.submit);
        final EditText edit = (EditText) findViewById(R.id.twitText);
        twitCon = this;
        choiceItems = getResources().getStringArray(R.array.choices_array);
        followerItems = getResources().getStringArray(R.array.sampleF1_array);
        followingItems = getResources().getStringArray(R.array.sampleF2_array);

        //define FeedMe class to call Toasties
        final FeedMe fm = new FeedMe(this);

        //spinner adapter
        ArrayAdapter<String> choicesAdapter = new ArrayAdapter<String>(twitCon, android.R.layout.simple_spinner_item, choiceItems);


        //use UI Spinner and load it
        s.setAdapter(choicesAdapter);


        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (choiceItems[position].equals("Feed")) {

                    //define adapter name
                    ArrayAdapter<String> mainListAdapter;

                    //connect to class to gain access to array you created
                    FeedMe fm2 = new FeedMe(twitCon);

                    //create your new arraylist
                    List<String> eatery = new ArrayList<String>();

                    //add all the array items from the class to the new list
                    eatery.addAll(fm2.loadArray());

                    //create adapter calling on the dynamic array from FeedMe Class // this will be dynamic data in week 3 from the API
                    mainListAdapter = new ArrayAdapter<String>(twitCon, android.R.layout.simple_list_item_1,  eatery);

                    //load adapter into listview
                    lv.setAdapter(mainListAdapter);

                    //populate toasty for the selected spinner item
                    fm.Toasty("Just Here To " + choiceItems[position]);

                } else if (choiceItems[position].equals("Followers")) {

                    //define adapter name
                    ArrayAdapter<String> mainListAdapter;

                    //create adapter to load static string array into list view // this will be dynamic data in week 3 from the API
                    mainListAdapter = new ArrayAdapter<String>(twitCon, android.R.layout.simple_list_item_1, followerItems);

                    //load adapter into listview
                    lv.setAdapter(mainListAdapter);

                    //populate toasty for the selected spinner item
                    fm.Toasty("Here's Your " + choiceItems[position]);

                } else if (choiceItems[position].equals("Following")) {

                    //define adapter name
                    ArrayAdapter<String> mainListAdapter;

                    //create adapter to load static string array into list view // this will be dynamic data in week 3 from the API
                    mainListAdapter = new ArrayAdapter<String>(twitCon, android.R.layout.simple_list_item_1, followingItems);

                    //load adapter into listview
                    lv.setAdapter(mainListAdapter);

                    //populate toasty for the selected spinner item
                    fm.Toasty("Who You Are " + choiceItems[position]);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //do nothing at this time
            }
        });

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


