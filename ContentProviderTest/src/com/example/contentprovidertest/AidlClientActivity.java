package com.example.contentprovidertest;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.androidsdk.jar.aidlserver.IAIDLServerService;

public class AidlClientActivity extends Activity {

	private TextView mTextView;
	private Button mButton;

	private IAIDLServerService mIaidlServerService = null;

	private ServiceConnection mConnection = new ServiceConnection() {

		public void onServiceDisconnected(ComponentName name) {
			mIaidlServerService = null;
		}

		public void onServiceConnected(ComponentName name, IBinder service) {
			Log.e("", "aidlͨ��");
			mIaidlServerService = IAIDLServerService.Stub.asInterface(service);
			// aidlͨ��
			try {
				String mText = "Say hello: " + mIaidlServerService.sayHello()
						+ "/n";
				mText += "����: " + mIaidlServerService.getBook().getBookName()
						+ "/n";
				mText += "�۸�: " + mIaidlServerService.getBook().getBookPrice();
				mTextView.setText(mText);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.testaidl);
		// ��ʼ���ؼ�
		mTextView = (TextView) findViewById(R.id.aidltv);
		mButton = (Button) findViewById(R.id.aidlbtn1);
		// �����¼���Ӧ
		mButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// bindService
				Log.e("", "onClick");
				Intent service = new Intent(
						"com.example.androidsdk.jar.aidlserver.IAIDLServerService");
				bindService(service, mConnection, BIND_AUTO_CREATE);
			}

		});
	}

}
