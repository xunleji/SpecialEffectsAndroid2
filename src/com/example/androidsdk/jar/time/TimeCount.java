package com.example.androidsdk.jar.time;

import java.util.Timer;
import java.util.TimerTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * 计时器
 * @author xujuan
 *
 */
public class TimeCount {

	public static Timer timer;
	public static TimerTask task;
	private int count = 0;
	
	public TimeCount() {
		super();
		// TODO Auto-generated constructor stub
	}
	//销毁计时器
	public void destoryTime(){
		if(task!=null){
			task.cancel();
		    task = null;
	    }
		if(timer!=null){
		   timer.cancel();
		   timer = null;
		}
	}
	
	public void excuteTime(final Handler handler){
		destoryTime();
		count = 10;
		timer = new Timer();
		task = new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				count--;
				Log.e("ddd", "count==="+count);
				if(count==0){
					Message msg = new Message();
					msg.what = 1;
					handler.sendMessage(msg);
				}
			}
		};
		if(timer!=null)timer.schedule(task, 0, 1000);
	}

}
