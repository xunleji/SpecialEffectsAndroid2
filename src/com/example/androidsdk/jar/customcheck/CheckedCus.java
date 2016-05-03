package com.example.androidsdk.jar.customcheck;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.example.androidsdk.R;
import com.example.androidsdk.jar.customcheck.WiperSwitch;
import com.example.androidsdk.jar.customcheck.WiperSwitch.OnChangedListener;

public class CheckedCus extends Activity implements OnChangedListener{
	
	WiperSwitch wiperSwitch ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.checkopenclose);
		//实例化WiperSwitch
		wiperSwitch = (WiperSwitch)findViewById(R.id.wiperSwitch1);
		//设置初始状态为false
		wiperSwitch.setChecked(false);
		//设置监听
		wiperSwitch.setOnChangedListener(this);
	}
	
	@Override
	public void OnChanged(WiperSwitch wiperSwitch, boolean checkState) {
		Log.e("log", "" + checkState);
	}

}
