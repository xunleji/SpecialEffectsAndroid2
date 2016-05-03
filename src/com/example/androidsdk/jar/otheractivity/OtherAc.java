package com.example.androidsdk.jar.otheractivity;

import com.example.androidsdk.R;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class OtherAc extends Activity {

	private LinearLayout layout;
	private Button btnSet;
	private Context friendContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.otherac);
		btnSet = (Button) findViewById(R.id.button1);
		layout = (LinearLayout) findViewById(R.id.layout);
		layout.setBackgroundResource(R.drawable.bg);
		try {
			friendContext = createPackageContext(
					"com.example.contentprovidertest",
					Context.CONTEXT_IGNORE_SECURITY);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		btnSet.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				new Handler().post(new Runnable() {
					@Override
					public void run() {
						layout.setBackgroundDrawable(friendContext
								.getResources().getDrawable(R.drawable.bg));
					}
				});
			}
		});
	}

}
