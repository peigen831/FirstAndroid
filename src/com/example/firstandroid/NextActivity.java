package com.example.firstandroid;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NextActivity extends ActionBarActivity {
	Connection connection;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    // Set the text view as the activity layout
	    setContentView(R.layout.fragment_next);
	    MainActivity.currentActivity = MainActivity.NEXT_ACTIVITY;
	    connection = Connection.getInstance();
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
	
	public void sendMessage1(View view){
		EditText txtField = (EditText)findViewById(R.id.edit_message1);
		String message = txtField.getText().toString();
		
		connection.sendMessage(message);
	}
	
	public static void showMessage1(String message){
		Toast.makeText(Connection.getContext(), message, Toast.LENGTH_SHORT).show();;
	}
}
