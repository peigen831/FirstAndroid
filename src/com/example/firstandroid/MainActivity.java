package com.example.firstandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	public final static String EXTRA_MESSAGE = "com.example.FirstAndroid.MESSAGE";
	public final static String MAIN_ACTIVITY = "main activity";
	public final static String NEXT_ACTIVITY = "next activity";
	public static String currentActivity;
	
	Connection connection;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		connection = new Connection(getApplicationContext());
		currentActivity = MAIN_ACTIVITY;
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
	
	public void nextPage(View view){
		Intent intent = new Intent(this, NextActivity.class);
		startActivity(intent);
	}
	
	public void sendMessage(View view){
		
		EditText txtField = (EditText)findViewById(R.id.edit_message);
		String message = txtField.getText().toString();

		Topic t = new Topic("Math");
		ChordMessage m = new ChordMessage(t);
		connection.sendTopic(m);
	}
	
	public static void showMessage(String message){
		Toast.makeText(Connection.getContext(), message, Toast.LENGTH_SHORT).show();
	}
}
