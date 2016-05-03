package com.example.androidsdk.jar.imageload;

import java.io.File;

import com.example.androidsdk.R;
import com.example.androidsdk.jar.imageload.ImageListening;
import com.example.androidsdk.jar.imageload.ImageLoad;
import com.example.androidsdk.jar.tool.Until;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class ImageLoadAc extends Activity {

	private ImageLoad imageLoad;
	private ImageListening imagelisten;
	private ImageView iv;
	private String url = "http://p1.qhimg.com/t0102d67cd8096d41dd.png";
	private Button btn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.imageload);
		String[] str = url.split("/");
		final String name = str[str.length-1];
		imageLoad = new ImageLoad();
		iv = (ImageView)findViewById(R.id.iamgeload);
		btn = (Button)findViewById(R.id.imagebtn);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				File file = new File(Until.getSDpath()+"/JARIMAGE/"+name);
				if(file.exists()){
					iv.setImageBitmap(Until.loadBitmapFromSDCard(Until.getSDpath()+"/JARIMAGE/"+name));
				}else{
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							imageLoad.setImageLoad(url, imagelisten, "JARIMAGE");
						}
					}).start();
				}
			}
		});
		imagelisten = new ImageListening() {
			
			@Override
			public void onResultListener() {
				runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						iv.setImageBitmap(Until.loadBitmapFromSDCard(Until.getSDpath()+"/JARIMAGE/"+name));
					}
				});
			}
		};
	}

}
