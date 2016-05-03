package com.example.androidsdk.jar.facejiance;

import com.example.androidsdk.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FaceTestActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.face);
		Button btn1 = (Button)findViewById(R.id.datafx);
		Button btn2 = (Button)findViewById(R.id.datayx);
		btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(FaceTestActivity.this, FangfaceActivity.class);
				startActivity(intent);
			}
		});
		btn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(FaceTestActivity.this, YuanfaceActivity.class);
				startActivity(intent);
				
			}
		});
	}

}
