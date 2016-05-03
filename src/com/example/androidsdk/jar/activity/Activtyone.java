package com.example.androidsdk.jar.activity;

import com.example.androidsdk.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Activtyone extends Activity {

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.e("Activtyone singleTop", "onStart");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.intents);
		Log.e("Activtyone singleTop", "onCreate");
		Button button = (Button) findViewById(R.id.get_music);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Activtyone.this, Activtytwo.class);
				startActivity(intent);
			}
		});
		
	}

}
