package com.example.androidsdk.jar.customcarman;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.example.androidsdk.Diary;
import com.example.androidsdk.R;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CarmanTest extends Activity {

	private SurfaceView surfaceView;
	private SurfaceHolder holder;
	private Camera camera;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.carmantest);
		surfaceView = (SurfaceView)findViewById(R.id.surfaceView1);
		holder = surfaceView.getHolder();
		holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);  
		holder.addCallback(new SurfaceHolder.Callback() {
			
			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				// TODO Auto-generated method stub
				try {
					camera = Camera.open();
					//设置预览
					camera.setPreviewDisplay(holder);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			@Override
			public void surfaceChanged(SurfaceHolder holder, int format, int width,
					int height) {
				// TODO Auto-generated method stub
				//获取相机参数
				Camera.Parameters parameters = camera.getParameters();
				//设置照片大小
//				parameters.setPreviewSize(176,144);
				//设置照片格式
				parameters.setPictureFormat(PixelFormat.JPEG);
				//设置相机参数
				camera.setParameters(parameters);
				camera.startPreview();
				
			}
			
			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				// TODO Auto-generated method stub
				camera.stopPreview();
				camera.release();
				camera=null;
			}
			
		});
		holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode==KeyEvent.KEYCODE_CAMERA||keyCode==KeyEvent.KEYCODE_SEARCH){
			takepic();
			return true;
		}
		return true;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		if(event.getAction()==MotionEvent.ACTION_DOWN){
			takepic();
			return true;
		}
		return true;
	}
	
	private void takepic(){
		camera.stopPreview();
		camera.takePicture(null, null, new PictureCallback() {
			
			@Override
			public void onPictureTaken(byte[] data, Camera camera) {
				// TODO Auto-generated method stub
				Diary.eLog("onPictureTaken");
				new SavePicTask().execute(data);
				camera.startPreview();
			}
		});
	}
	
	class SavePicTask extends AsyncTask<byte[], String, String>{

		@Override
		protected String doInBackground(byte[]... params) {
			// TODO Auto-generated method stub
			try {
				File file = new File(getSDpath(), "pic.jpg");
				if(file.exists())file.delete();
				FileOutputStream fos = new FileOutputStream(file);
				fos.write(params[0]);
				fos.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
	}
	
	//获取SD卡路径
    public static String getSDpath(){
    	File sdDir = null;
    	try {
			boolean sdCardExist = Environment.getExternalStorageState().equals(
					android.os.Environment.MEDIA_MOUNTED); 
			if (sdCardExist) {
				sdDir = Environment.getExternalStorageDirectory();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sdDir == null?"null":sdDir.toString();
    }

}
