package com.grinddesign.madlib;

import java.util.ArrayList;
import android.app.Activity;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends Activity {

	String name;
	String profession;
	String adjective;
	String action;
	String body;
	String feeling;
	float number;
	String time;
	boolean running;
	LinearLayout ll;
	LinearLayout.LayoutParams lp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN); 
		
		setContentView(buildForm());
		
	}

	private View buildForm() {
		View v = new View(this);
		ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		ll.setBackgroundColor(Color.WHITE);
		lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		ll.setLayoutParams(lp);

		
		
		TextView header = new TextView(this);
		header.setTextColor(Color.RED);
		header.setText("Welcome to MadLib. Please fill out the form below and when you click \"MadLib Me\" it will show you your story.");
		ll.addView(header);
		
		ScrollView sc = new ScrollView(getApplicationContext());
	    sc.setLayoutParams(lp);

		ArrayList<View> allViews = new ArrayList<View>();

		TextView lbl1 = new TextView(this);
		lbl1.setText("Person's Name");

		final EditText edit1 = new EditText(this);
		edit1.setHint("Enter a Name");

		TextView lbl2 = new TextView(this);
		lbl2.setText("Enter A Profession");

		final EditText edit2 = new EditText(this);
		edit2.setHint("Name of Profession");

		TextView lbl3 = new TextView(this);
		lbl3.setText("Adjective");

		final EditText edit3 = new EditText(this);
		edit3.setHint("Enter an Adjective");

		CheckBox cb = new CheckBox(this);
		cb.setText("Are You Running?");
		running = cb.isChecked();

		TextView lbl5 = new TextView(this);
		lbl5.setText("Body Part");

		final EditText edit5 = new EditText(this);
		edit5.setHint("Enter a Body Part");

		TextView lbl6 = new TextView(this);
		lbl6.setText("Feeling");

		final EditText edit6 = new EditText(this);
		edit6.setHint("Enter a Feeling");

		TextView lbl7 = new TextView(this);
		lbl7.setText("Number");

		final EditText edit7 = new EditText(this);
		edit7.setHint("Enter a Number");
		edit7.setInputType(InputType.TYPE_CLASS_PHONE);

		TextView lbl8 = new TextView(this);
		lbl8.setText("Period of Time");

		final EditText edit8 = new EditText(this);
		edit8.setHint("Enter a Period of Time");

		LinearLayout form1 = new LinearLayout(this);
		form1.setOrientation(LinearLayout.HORIZONTAL);
		form1.setLayoutParams(lp);

		form1.addView(lbl1);
		form1.addView(edit1);
		allViews.add(form1);

		LinearLayout form2 = new LinearLayout(this);
		form2.setOrientation(LinearLayout.HORIZONTAL);
		form2.setLayoutParams(lp);

		form2.addView(lbl2);
		form2.addView(edit2);
		allViews.add(form2);

		LinearLayout form3 = new LinearLayout(this);
		form3.setOrientation(LinearLayout.HORIZONTAL);
		form3.setLayoutParams(lp);

		form3.addView(lbl3);
		form3.addView(edit3);
		allViews.add(form3);

		LinearLayout form4 = new LinearLayout(this);
		form4.setOrientation(LinearLayout.HORIZONTAL);
		form4.setLayoutParams(lp);

		form4.addView(cb);
		allViews.add(form4);

		LinearLayout form5 = new LinearLayout(this);
		form5.setOrientation(LinearLayout.HORIZONTAL);
		form5.setLayoutParams(lp);

		form5.addView(lbl5);
		form5.addView(edit5);
		allViews.add(form5);

		LinearLayout form6 = new LinearLayout(this);
		form6.setOrientation(LinearLayout.HORIZONTAL);
		form6.setLayoutParams(lp);

		form6.addView(lbl6);
		form6.addView(edit6);
		allViews.add(form6);

		LinearLayout form7 = new LinearLayout(this);
		form7.setOrientation(LinearLayout.HORIZONTAL);
		form7.setLayoutParams(lp);

		form7.addView(lbl7);
		form7.addView(edit7);
		allViews.add(form7);

		LinearLayout form8 = new LinearLayout(this);
		form8.setOrientation(LinearLayout.HORIZONTAL);
		form8.setLayoutParams(lp);

		form8.addView(lbl8);
		form8.addView(edit8);
		allViews.add(form8);

		final TextView madlib = new TextView(this);
		madlib.setLayoutParams(lp);

		Button lib = new Button(this);
		lib.setText("MadLib Me");
		lib.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				name = edit1.getText().toString();
				profession = edit2.getText().toString();
				adjective = edit3.getText().toString();
				if (running) {
					action = "go running";
				}
				else {
					action = "not go running";
				}
				body = edit5.getText().toString();
				feeling = edit6.getText().toString();
				if (edit7.getText().length() != 0) {
					number = Integer.parseInt(edit7.getText().toString()) / 2.0f;
				}
				else {

				}
				time = edit8.getText().toString();

				if (edit1.getText().length() == 0) {

					edit1.setError("please input name");

				}
				else if (edit2.getText().length() == 0) {

					edit2.setError("please input profession");

				}
				else if (edit3.getText().length() == 0) {

					edit3.setError("please input adjective");

				}
				else if (edit5.getText().length() == 0) {

					edit5.setError("please input body part");
				}
				else if (edit6.getText().length() == 0) {

					edit6.setError("please input feeling");

				}
				else if (edit7.getText().length() == 0) {

					edit7.setError("please input number");

				}
				else if (edit8.getText().length() == 0) {

					edit8.setError("please input period of time");

				}
				else {

					edit1.setError(null);
					edit2.setError(null);
					edit3.setError(null);
					edit5.setError(null);
					edit6.setError(null);
					edit7.setError(null);
					edit8.setError(null);
					madlib.setText("Hi, My Name is " + name + 
							" and I am a " + profession + 
							" who has a(n) " + adjective + 
							" way to teach others to " + action + 
							" to help strengthen their injured " + body + 
							" I demonstrate how to increase the " + feeling + 
							" feeling by repeating this as many as " + number + 
							" times every " + number + 
							" " + time);

				}
			} 
		});

		if (allViews.size() > 0) {
			for(View lines : allViews) {
				ll.addView(lines);
			}
		}

		
		ll.addView(lib);


		ll.addView(madlib);
		
		sc.addView(ll);

		v = sc;

		return v;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
