package com.example.androidsdk.jar.inputmethodmanager;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * ����Android�е������
 * @author xujuan
 *
 */
public class InputMethodManagerTest extends Activity implements OnClickListener{

	private Button button;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 LinearLayout layout=new LinearLayout(this);  
		 LinearLayout.LayoutParams layoutParams=new LinearLayout.
		 		LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
		 				LinearLayout.LayoutParams.WRAP_CONTENT);  
		 button=new Button(this);  
		 button.setId(123);  
		 button.setText("Hello GaoMatrix");  
		 button.setOnClickListener(this);  
		 layout.addView(button, layoutParams);  
		 setContentView(layout); 
		 Timer timer = new Timer();
		 timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				 InputMethodManager inputMethodManager=(InputMethodManager) 
				 		getSystemService(Context.INPUT_METHOD_SERVICE);  
				 //��������ڽ������л����뷨�Ĺ��ܣ�������뷨������ʵ״̬���ͽ������أ������������״̬������ʾ���뷨
				 inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);  

			}
		}, 2000);

	}
	
	@Override
	public void onClick(View v) {
		InputMethodManager inputMethodManager=(InputMethodManager) 
			getSystemService(Context.INPUT_METHOD_SERVICE);  
		inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS); 

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

}
