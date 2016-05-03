package com.example.androidsdk.jar.loading;

import com.example.androidsdk.R;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

public class LoadingAc extends Activity {

	private ImageView iv;
	private int alpha = 255;
	private Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if(iv!=null){
				iv.setAlpha(msg.arg1);
				iv.invalidate();
			}
		}
		
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ivload); 
		iv = (ImageView)findViewById(R.id.imageView1);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while (alpha>0) {
					try {
						alpha-=10;
						Thread.sleep(200);
						Message msg = new Message();
						msg.arg1 = alpha;
						handler.sendMessage(msg);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

}
