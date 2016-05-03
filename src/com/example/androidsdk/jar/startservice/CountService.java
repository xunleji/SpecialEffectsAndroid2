package com.example.androidsdk.jar.startservice;

import android.app.Service;
import android.os.IBinder;
import android.os.Binder;
import android.content.Intent;
import android.util.Log;
/**�����ķ���*/
public class CountService extends Service{
		/**��������*/
	private boolean threadDisable ;
	private int count;
	
	public void onCreate(){
		super.onCreate();
		/**����һ���̣߳�ÿ���������һ�����ڿ���̨����Log���*/
		new Thread(new Runnable(){
			public void run(){
				while(!threadDisable){
					try{
						Thread.sleep(1000);
					}catch(InterruptedException e){
						e.printStackTrace();
					}
					count++;
					Log.v("CountService","Count is"+count);
				}
			}
		}).start();
	}
	
	public IBinder onBind(Intent intent){
		return null;
	}
	public void onDestroy(){
		super.onDestroy();
			/**����ֹͣʱ����ֹ��������*/
		this.threadDisable = true;
	}
	public int getConunt(){
		return count;
	}
	public class ServiceBinder extends Binder{
		public CountService getService(){
			return CountService.this;
		}
	}
}
