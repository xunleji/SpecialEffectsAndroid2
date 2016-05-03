package com.example.androidsdk.jar.music;

import com.example.androidsdk.R;
import com.example.androidsdk.jar.music.MusicService;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * 播放器
 * @author xujuan
 *
 */
public class MusicDemo extends Activity implements OnClickListener{
	
	private Button mPrevious,mPlay,mNext,mPause;
	private ComponentName component;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.music);
        //oncreate里代码一如既往的少
        setupViews();
	}
	//初始化一些工作
    public void setupViews(){
    	component = new ComponentName(this,
				MusicService.class);
    	
    	mPrevious = (Button)findViewById(R.id.previous);
    	mPlay = (Button)findViewById(R.id.play);
    	mNext = (Button)findViewById(R.id.next);
    	mPause = (Button)findViewById(R.id.pause);
    	
    	mPrevious.setOnClickListener(this);
    	mPlay.setOnClickListener(this);
    	mNext.setOnClickListener(this);
    	mPause.setOnClickListener(this);
    }
	@Override
	public void onClick(View v) {
		if(v == mPrevious){
			Intent mIntent = new Intent(MusicService.PREVIOUS_ACTION);
			mIntent.setComponent(component);
			startService(mIntent);
		}else if(v == mPlay){
			Intent mIntent = new Intent(MusicService.PLAY_ACTION);
			mIntent.setComponent(component);
			startService(mIntent);
		}else if(v == mNext){
			Intent mIntent = new Intent(MusicService.NEXT_ACTION);
			mIntent.setComponent(component);
			startService(mIntent);
		}else{
			Intent mIntent = new Intent(MusicService.PAUSE_ACTION);
			mIntent.setComponent(component);
			startService(mIntent);
		}
	}

}
