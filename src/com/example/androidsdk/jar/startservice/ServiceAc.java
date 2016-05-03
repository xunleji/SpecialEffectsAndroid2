package com.example.androidsdk.jar.startservice;

import com.example.androidsdk.R;
import com.example.androidsdk.jar.startservice.CountService;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ServiceAc extends Activity implements OnClickListener {

	private Button startservice, stopservice;
	private Button bindservice, unbindservice;
	CountService countService;
	private ServiceConnection conn = new ServiceConnection() {
		/** 获取服务对象时的操作 */
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			countService = ((CountService.ServiceBinder) service).getService();

		}

		/** 无法获取到服务对象时的操作 */
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			countService = null;
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.service);

		startservice = (Button) findViewById(R.id.startservice);
		stopservice = (Button) findViewById(R.id.stopservice);
		bindservice = (Button) findViewById(R.id.bindservice);
		unbindservice = (Button) findViewById(R.id.unbindservice);
		startservice.setOnClickListener(this);
		stopservice.setOnClickListener(this);
		bindservice.setOnClickListener(this);
		unbindservice.setOnClickListener(this);
	}

	private Intent intent;

	@Override
	public void onClick(View v) {
		if (v == startservice) {
			intent = new Intent(ServiceAc.this, CountService.class);
			startService(intent);
		} else if (v == stopservice) {
			intent = new Intent(ServiceAc.this, CountService.class);
			stopService(intent);
		} else if (v == bindservice) {
			intent = new Intent(ServiceAc.this, CountService.class);
			bindService(intent, conn, Context.BIND_AUTO_CREATE);
		} else if (v == unbindservice) {
			unbindService(conn);
		}
	}

}
