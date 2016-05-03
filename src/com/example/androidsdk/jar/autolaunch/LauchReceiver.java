package com.example.androidsdk.jar.autolaunch;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class LauchReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent arg1) {
		try { 
			Thread.sleep(2000); 
		} catch (InterruptedException e) { 
		 // TODO Auto-generated catch block  
			e.printStackTrace(); 
		} 
		Intent i = new Intent(context, BootupDemoActivity.class); 
		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
		context.startActivity(i); 
	}

}
