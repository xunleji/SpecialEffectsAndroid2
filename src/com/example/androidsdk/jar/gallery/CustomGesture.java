package com.example.androidsdk.jar.gallery;

import com.example.androidsdk.Diary;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;

public class CustomGesture extends Activity implements OnTouchListener{

	private GestureDetector gestureDetector;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Button btn = new Button(this);
		btn.setText("btn");
		btn.setOnTouchListener(this);
		setContentView(btn);
		gestureDetector = new GestureDetector(this, new GestureListener());
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		return gestureDetector.onTouchEvent(event);
	}
	
	class GestureListener implements GestureDetector.OnDoubleTapListener,OnGestureListener{

		//����ʱ����
		@Override
		public boolean onDown(MotionEvent e) {
			Log.e("GestureListener", "onDown");
			return false;
		}

		//Touch�˻�û�л���ʱ���� 
		@Override
		public void onShowPress(MotionEvent e) {
			Log.e("GestureListener", "onShowPress");
		}
		//���һ�·ǳ���ģ���������onDown->onSingleTapUp->onSingleTapConfirmed 
		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			Log.e("GestureListener", "onSingleTapUp");
			return false;
		}
		//Touch�˻���ʱ����
		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {
			Log.e("GestureListener", "onScroll");
			return false;
		}
		//Touch�˲��ƶ�һֱTouch downʱ���� 
		@Override
		public void onLongPress(MotionEvent e) {
			Log.e("GestureListener", "onLongPress");
		}
		//Touch�˻���һ������upʱ����
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			Log.e("GestureListener", "onFling");
			return false;
		}
	
		//���һ����΢����ģ���������onDown->onShowPress->onSingleTapUp->onSingleTapConfirmed
		@Override
		public boolean onSingleTapConfirmed(MotionEvent e) {
			Log.e("GestureListener", "onSingleTapConfirmed");
			return false;
		}
		//˫���ĵڶ���Touch downʱ���� 
		@Override
		public boolean onDoubleTap(MotionEvent e) {
			Log.e("GestureListener", "onDoubleTap");
			return false;
		}
		//˫���ĵڶ���Touch down��up���ᴥ��������e.getAction()���֡�
		@Override
		public boolean onDoubleTapEvent(MotionEvent e) {
			Log.e("GestureListener", "onDoubleTapEvent");
			return false;
		}
		
	}

}
