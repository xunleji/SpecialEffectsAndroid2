package com.example.androidsdk.jar.parcelableserializable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;

public class Test extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		TextView tv = new TextView(this);
		tv.setText("–Ú¡–ªØ");
		tv.setGravity(Gravity.CENTER);
		tv.setTextSize(20);
		setContentView(tv);   
        Intent i = getIntent();   
        boolean state = i.getBooleanExtra("state", false);
        if(state){
        	PersonParceable p = i.getParcelableExtra("yes");   
        	Log.e("Test","id="+p.getId());   
        	Log.e("Test","name="+p.getName());   
        }else if(!state){
        	PersonSerializable ps =(PersonSerializable)i.getSerializableExtra("no");
        	Log.e("Test","id="+ps.getId());   
        	Log.e("Test","name="+ps.getName());   
        }
	}

}
