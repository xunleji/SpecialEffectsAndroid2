package com.example.androidsdk.jar.activity;

import com.example.androidsdk.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Activtytwo extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.intents);
		Log.e("Activtytwo singleInstance", "onCreate");
		Button button = (Button) findViewById(R.id.get_music);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Activtytwo.this, Activtythree.class);
				startActivity(intent);
			}
		});
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.e("Activtytwo singleInstance", "onStart");
	}

}
