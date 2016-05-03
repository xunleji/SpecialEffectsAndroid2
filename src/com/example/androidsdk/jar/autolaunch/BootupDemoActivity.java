package com.example.androidsdk.jar.autolaunch;

import com.example.androidsdk.R;

import android.app.Activity;
import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.os.Bundle;

public class BootupDemoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.helloworld);
		KeyguardManager keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
		KeyguardLock lock = keyguardManager.newKeyguardLock(KEYGUARD_SERVICE);
		//½âËøÆÁÄ»
		lock.disableKeyguard();
	}

}
