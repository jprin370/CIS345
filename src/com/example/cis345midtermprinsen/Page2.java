package com.example.cis345midtermprinsen;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Page2 extends Activity {
	
	TextView firstName, lastName, tvSelectedDate, welcome;
	EditText etAnswer1, etAnswer2;
	String gotBread;
	DatePicker dpSurveryDate;
	Button btnDateEdit, btnSubmitSurvery;
	CheckBox checked;
	
	private int year;
	private int month;
	private int day;
	
	static final int DATE_DIALOG_ID = 999;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page2);
		
		welcome = (TextView) findViewById(R.id.tvWelcome);
		Bundle gotBasket = getIntent().getExtras();

		welcome.setText("Thanks for taking this survery " + gotBasket.getString("firstName") + " " + gotBasket.getString("lastName"));
		
		setCurrentDateOnView();
		addListenerOnButton();
		initialize();
	}
	
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
	      //Toast.makeText(this, "Menu Item 1 selected", Toast.LENGTH_SHORT)
	          //.show();
	    	Intent intent = new Intent(Page2.this, MainActivity.class);
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

	private void setCurrentDateOnView() {
		
		//http://www.mkyong.com/android/android-date-picker-example/
		dpSurveryDate = (DatePicker) findViewById(R.id.dpSurveryDate);
		tvSelectedDate = (TextView) findViewById(R.id.tvSelectedDate);
		
		final Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);
		
		// set current date into textview
		tvSelectedDate.setText(new StringBuilder()
			// Month is 0 based, just add 1
			.append(month + 1).append("-").append(day).append("-")
			.append(year).append(" "));
 
		// set current date into datepicker
		dpSurveryDate.init(year, month, day, null);
		
	}
	
	private void addListenerOnButton() {
		btnDateEdit = (Button) findViewById(R.id.btnDateEdit);
		
		btnDateEdit.setOnClickListener(new View.OnClickListener() {
 
			@Override
			public void onClick(View v) {
 
				showDialog(DATE_DIALOG_ID);
 
			}
 
		});
		
	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_ID:
		   // set date picker as current date
		   return new DatePickerDialog(this, datePickerListener, 
                         year, month,day);
		}
		return null;
	}
	
	private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

			// when dialog box is closed, below method will be called.
				public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
					year = selectedYear;
					month = selectedMonth;
					day = selectedDay;

			// set selected date into textview
				tvSelectedDate.setText(new StringBuilder().append(month + 1)
			   .append("-").append(day).append("-").append(year)
			   .append(" "));
					
			// set selected date into datepicker
				dpSurveryDate.init(year, month, day, null);

				}
	};

	private void initialize() {
		etAnswer1 = (EditText) findViewById(R.id.etAnswer1);
		etAnswer2 = (EditText) findViewById(R.id.etAnswer2);
		checked = (CheckBox) findViewById(R.id.cbYes);
		btnSubmitSurvery = (Button) findViewById(R.id.btnSubmitSurvery);
		final Bundle basket = new Bundle();
		final RadioGroup rgSelection = (RadioGroup) findViewById(R.id.rgSelection);
		final RadioButton rBQB = (RadioButton) findViewById(R.id.rbQB);
		final RadioButton rBRB = (RadioButton) findViewById(R.id.rbRB);
		final RadioButton rBWR = (RadioButton) findViewById(R.id.rbWR);
		final RadioButton rBTE = (RadioButton) findViewById(R.id.rbTE);
		Log.i("MyPrinesn", "still works");

		btnSubmitSurvery.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				try {
					Log.i("MyPrinesn", "TryStatement");
					final String answer1 = etAnswer1.getText().toString();
					Log.i("MyPrinesn", answer1);
					final String answer2 = etAnswer2.getText().toString();
					Log.i("MyPrinesn", answer2);
					String answer3;
					
					if (checked.isChecked())
					{
						answer3 = "My favorite league IS a PPR league";
						Log.i("MyPrinesn", answer3);
					}
					else
					{
						answer3 = "My favorite league is NOT a PPR league";
						Log.i("MyPrinesn", answer3);
					}
					String answer4;
					if (rgSelection.getCheckedRadioButtonId() == rBQB.getId())
						answer4 = "QB";
					else if (rgSelection.getCheckedRadioButtonId() == rBRB.getId())
						answer4 = "RB";
					else if (rgSelection.getCheckedRadioButtonId() == rBWR.getId())
						answer4 = "WR";
					else if (rgSelection.getCheckedRadioButtonId() == rBTE.getId())
						answer4 = "TE";
					else
						answer4 = "None";
					Log.i("MyPrinesn", answer4);
					
					final String answer5 = (String) tvSelectedDate.getText();
					Log.i("MyPrinesn", answer5);
					
					basket.putString("answer1", answer1);
					basket.putString("answer2", answer2);
					basket.putString("answer3", answer3);
					basket.putString("answer4", answer4);
					basket.putString("answer5", answer5);
					
					Intent intent = new Intent(Page2.this, Page3.class);

					intent.putExtras(basket);
					
					Log.i("MyPrinesn", "StartingPage3");
					startActivity(intent);
				}
				catch (Exception exc) {
					if(exc instanceof NullPointerException || exc instanceof NumberFormatException)
					{
						Log.i("Prinsen", "CatchThrown");
						Context context = getApplicationContext();
						CharSequence text = "Please answer all questions";
						int duration = Toast.LENGTH_SHORT;
						Toast toast = Toast.makeText(context, text, duration);
						toast.show();
					}
				}	
			}
		});
		
	}  
	
}
