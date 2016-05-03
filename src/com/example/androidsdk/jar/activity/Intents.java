package com.example.androidsdk.jar.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.androidsdk.R;

public class Intents extends Activity {

	private int IMAGE = 0;
	private int CARMAN_PICTURE = 1;
	private Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.intents);
		Button button = (Button) findViewById(R.id.get_music);
		button.setOnClickListener(mGetMusicListener);

	}

	private OnClickListener mGetMusicListener = new OnClickListener() {
		public void onClick(View v) {
			//选择图片
			intent = new Intent(Intent.ACTION_GET_CONTENT);
			intent.setType("image/*"); 
			Intent wrapperIntent = Intent.createChooser(intent, "select");
			startActivityForResult(wrapperIntent, IMAGE);
			
//			intent = new Intent(Intents.this, Activtyone.class);
//			//如果activity在task存在，拿到最顶端,不会启动新的Activity
//			intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
//			//如果activity在task存在，将Activity之上的所有Activity结束掉
//			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//			//默认的跳转类型,将Activity放到一个新的Task中
//			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//			//如果Activity已经运行到了Task，再次跳转不会在运行这个Activity
//			intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//			startActivity(intent);
		}
	};
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode==IMAGE){
			if(resultCode==RESULT_OK){
				Uri uri = data.getData();
				Log.e("Uri", "IMAGE="+uri);
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

}
