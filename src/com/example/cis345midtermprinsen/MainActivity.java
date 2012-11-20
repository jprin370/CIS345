package com.example.cis345midtermprinsen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity {

	String bread;
	EditText firstName, lastName, address, age;
	Button send;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
       }
	
	//How to work with an ActionBar
	// http://www.vogella.com/articles/Android/article.html#menu
	 @Override
	  public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.mainmenu, menu);
	    return true;
	  }
	 
	 @Override
	  public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    case R.id.menuitem1:
	    	Intent intent = new Intent(MainActivity.this, MainActivity.class);
	    	startActivity(intent);
	      break;
	    case R.id.menuitem2:
	      Toast.makeText(this, "This is a survery app for CIS341 by Jason Prinsen", Toast.LENGTH_SHORT)
	          .show();
	      break;

	    default:
	      break;
	    }

	    return true;
	  }

	private void initialize() {
		firstName = (EditText) findViewById(R.id.etFirstName);
		lastName = (EditText) findViewById(R.id.etLastName);
		address = (EditText) findViewById(R.id.etAddress);
		age = (EditText) findViewById(R.id.etAge);
		send = (Button) findViewById(R.id.btnSend);
		final Bundle basket = new Bundle();
		final RadioGroup sexSelection = (RadioGroup) findViewById(R.id.radioSex);
		final RadioButton rBMale = (RadioButton) findViewById(R.id.radioMale);
		final RadioButton rBFemale = (RadioButton) findViewById(R.id.radioFemale);
		Log.i("MyPrinesn", "still works");

		send.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				try {
					Log.i("MyPrinesn", "TryStatement");
					final String firstNameContent = firstName.getText().toString();
					Log.i("MyPrinesn", firstNameContent);
					final String lastNameContent = lastName.getText().toString();
					Log.i("MyPrinesn", lastNameContent);
					final String addressContent = address.getText().toString();
					Log.i("MyPrinesn", addressContent);
					final int ageContent = Integer.parseInt(age.getText().toString());
					Log.i("MyPrinesn", String.valueOf(ageContent));
					String sex = "Sex not Selected";
					Log.i("MyPrinesn", "Select Sex");
					if (sexSelection.getCheckedRadioButtonId() == rBMale.getId())
						sex = "male";
					else if (sexSelection.getCheckedRadioButtonId() == rBFemale.getId())
						sex = "female";
					Log.i("MyPrinesn", sex);
					basket.putString("firstName", firstNameContent);
					basket.putString("lastName", lastNameContent);
					
					Intent intent = new Intent(MainActivity.this, Page2.class);
					intent.putExtras(basket);
					startActivity(intent);
				}
				catch (Exception exc) {
					if(exc instanceof NullPointerException || exc instanceof NumberFormatException)
					{
						Log.i("Prinsen", "CatchThrown");
						Context context = getApplicationContext();
						CharSequence text = "Please fill in all fields";
						int duration = Toast.LENGTH_SHORT;
						Toast toast = Toast.makeText(context, text, duration);
						toast.show();
					}
				}	
			}
		});
		
	}   
}
