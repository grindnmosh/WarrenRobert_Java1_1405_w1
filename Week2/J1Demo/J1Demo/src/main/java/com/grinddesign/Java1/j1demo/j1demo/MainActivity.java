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
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.grindesign.listView.FeedMe;


public class MainActivity extends Activity {
    Context twitCon;
    String[] choiceItems;
    String[] followerItems;
    String[] followingItems;
    String[] feedItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setContentView(BuildSpinner());

        //initializing constants
        Spinner s = (Spinner) findViewById(R.id.choices);
        final ListView lv = (ListView) findViewById(R.id.mainList);
        twitCon = this;
        choiceItems = getResources().getStringArray(R.array.choices_array);
        feedItems = getResources().getStringArray(R.array.sampleFeed_array);
        followerItems = getResources().getStringArray(R.array.sampleF1_array);
        followingItems = getResources().getStringArray(R.array.sampleF2_array);

        final FeedMe fm = new FeedMe(this);


        //spinner adapter
        ArrayAdapter choicesAdapter = new ArrayAdapter(twitCon, android.R.layout.simple_spinner_item, choiceItems);

        //use UI Spinner and load it
        s.setAdapter(choicesAdapter);

        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (choiceItems[position].equals("Feed"))
                {
                    ArrayAdapter<String> mainListAdapter;
                    mainListAdapter = new ArrayAdapter<String>(twitCon, android.R.layout.simple_list_item_1, feedItems);
                    lv.setAdapter(mainListAdapter);
                    fm.Toasty("Just Here To " + choiceItems[position]);
                }
                else if (choiceItems[position].equals("Followers"))
                {

                    ArrayAdapter<String> mainListAdapter;
                    mainListAdapter = new ArrayAdapter<String>(twitCon, android.R.layout.simple_list_item_1, followerItems);
                    lv.setAdapter(mainListAdapter);
                    Toast.makeText(twitCon, "Here's Your " + choiceItems[position], Toast.LENGTH_LONG).show();
                }
                else if (choiceItems[position].equals("Following"))
                {
                    ArrayAdapter<String> mainListAdapter;
                    mainListAdapter = new ArrayAdapter<String>(twitCon, android.R.layout.simple_list_item_1, followingItems
                    );
                    lv.setAdapter(mainListAdapter);
                    Toast.makeText(twitCon, "Who You Are " + choiceItems[position], Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> mainListAdapter = new ArrayAdapter<String>(twitCon, android.R.layout.simple_list_item_1, feedItems);
        lv.setAdapter(mainListAdapter);


    }


 /*   private Spinner BuildSpinner() {
        //initializing constants
        Spinner s = (Spinner) findViewById(R.id.choices);
        twitCon = this;
        choiceItems = getResources().getStringArray(R.array.choices_array);

        //spinner adapter
        ArrayAdapter choicesAdapter = new ArrayAdapter(twitCon, android.R.layout.simple_spinner_item, choiceItems);

        s.setAdapter(choicesAdapter);


        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (choiceItems[position].equals("Feed"))
                {
                    Toast.makeText(twitCon, "Just Here To " + choiceItems[position], Toast.LENGTH_LONG).show();
                }
                else if (choiceItems[position].equals("Followers"))
                {
                    Toast.makeText(twitCon, "Here's Your " + choiceItems[position], Toast.LENGTH_LONG).show();
                }
                else if (choiceItems[position].equals("Following"))
                {
                    Toast.makeText(twitCon, "Who You Are " + choiceItems[position], Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> mainListAdapter = new ArrayAdapter<String>(twitCon, android.R.layout.simple_list_item_1, choiceItems);
        lv.setAdapter(mainListAdapter);

        Spinner vS = Not sure what to return or how to call it to run this off function

        return vS;

    }*/


}
