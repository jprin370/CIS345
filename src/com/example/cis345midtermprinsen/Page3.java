package com.example.cis345midtermprinsen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Page3 extends Activity {
	
	TextView tvResponse1, tvResponse2, tvResponse3, tvResponse4, tvResponse5;
	Button btnFinalSubmit;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page3);
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
	    	Intent intent = new Intent(Page3.this, MainActivity.class);
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
		tvResponse1 = (TextView) findViewById(R.id.tvResponse1);
		tvResponse2 = (TextView) findViewById(R.id.tvResponse2);
		tvResponse3 = (TextView) findViewById(R.id.tvResponse3);
		tvResponse4 = (TextView) findViewById(R.id.tvResponse4);
		tvResponse5 = (TextView) findViewById(R.id.tvResponse5);
		
		Bundle gotBasket = getIntent().getExtras();

		tvResponse1.setText(gotBasket.getString("answer1"));
		tvResponse2.setText(gotBasket.getString("answer2"));
		tvResponse3.setText(gotBasket.getString("answer3"));
		tvResponse4.setText(gotBasket.getString("answer4"));
		tvResponse5.setText(gotBasket.getString("answer5"));
		
	btnFinalSubmit = (Button) findViewById(R.id.btnFinalSubmit);
		
		btnFinalSubmit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Context context = getApplicationContext();
				CharSequence text = "You have successfully submitted this Survery!";
				int duration = Toast.LENGTH_SHORT;
				Toast toast = Toast.makeText(context, text, duration);
				toast.show();
				
			}
		});
		
	}
}
