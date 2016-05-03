package com.example.androidsdk.jar.downfile;

import com.example.androidsdk.jar.downfile.DirectDownloader;

import android.app.Activity;
import android.os.Bundle;

public class NotificationCancel extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		DirectDownloader direct = new DirectDownloader(this);
		direct.cancelNotification();
	}

}
