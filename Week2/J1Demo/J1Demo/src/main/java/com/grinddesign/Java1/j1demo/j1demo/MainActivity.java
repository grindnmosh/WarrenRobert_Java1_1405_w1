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
    Context twitCon;
    String[] choiceItems;
    String[] followerItems;
    String[] followingItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setContentView(BuildSpinner());

        //initializing constants
        Spinner s = (Spinner) findViewById(R.id.choices);
        final ListView lv = (ListView) findViewById(R.id.mainList);
        final Button sub = (Button) findViewById(R.id.submit);
        final EditText edit = (EditText) findViewById(R.id.twitText);
        twitCon = this;
        choiceItems = getResources().getStringArray(R.array.choices_array);
        followerItems = getResources().getStringArray(R.array.sampleF1_array);
        followingItems = getResources().getStringArray(R.array.sampleF2_array);

        final FeedMe fm = new FeedMe(this);

        //spinner adapter
        ArrayAdapter<String> choicesAdapter = new ArrayAdapter<String>(twitCon, android.R.layout.simple_spinner_item, choiceItems);


        //use UI Spinner and load it
        s.setAdapter(choicesAdapter);


        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (choiceItems[position].equals("Feed")) {
                    ArrayAdapter<String> mainListAdapter;
                    FeedMe fm2 = new FeedMe(twitCon);
                    List<String> eatery = new ArrayList<String>();
                    eatery.addAll(fm2.loadArray());
                    mainListAdapter = new ArrayAdapter<String>(twitCon, android.R.layout.simple_list_item_1,  eatery);
                    lv.setAdapter(mainListAdapter);
                    fm.Toasty("Just Here To " + choiceItems[position]);
                } else if (choiceItems[position].equals("Followers")) {

                    ArrayAdapter<String> mainListAdapter;
                    mainListAdapter = new ArrayAdapter<String>(twitCon, android.R.layout.simple_list_item_1, followerItems);
                    lv.setAdapter(mainListAdapter);
                    fm.Toasty("Here's Your " + choiceItems[position]);
                } else if (choiceItems[position].equals("Following")) {
                    ArrayAdapter<String> mainListAdapter;
                    mainListAdapter = new ArrayAdapter<String>(twitCon, android.R.layout.simple_list_item_1, followingItems
                    );
                    lv.setAdapter(mainListAdapter);
                    fm.Toasty("Who You Are " + choiceItems[position]);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit.getText().length() == 0) {

                    fm.Toasty("Please enter a tweet");

                }
                else {
                    fm.Toasty("Your Tweet would have been submitted if this were week 3");
                    edit.setText("");
                }

            }

        });




    }
}


