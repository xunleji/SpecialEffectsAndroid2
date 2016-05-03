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

		//按下时触发
		@Override
		public boolean onDown(MotionEvent e) {
			Log.e("GestureListener", "onDown");
			return false;
		}

		//Touch了还没有滑动时触发 
		@Override
		public void onShowPress(MotionEvent e) {
			Log.e("GestureListener", "onShowPress");
		}
		//点击一下非常快的（不滑动）onDown->onSingleTapUp->onSingleTapConfirmed 
		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			Log.e("GestureListener", "onSingleTapUp");
			return false;
		}
		//Touch了滑动时触发
		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {
			Log.e("GestureListener", "onScroll");
			return false;
		}
		//Touch了不移动一直Touch down时触发 
		@Override
		public void onLongPress(MotionEvent e) {
			Log.e("GestureListener", "onLongPress");
		}
		//Touch了滑动一点距离后，up时触发
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			Log.e("GestureListener", "onFling");
			return false;
		}
	
		//点击一下稍微慢点的（不滑动）onDown->onShowPress->onSingleTapUp->onSingleTapConfirmed
		@Override
		public boolean onSingleTapConfirmed(MotionEvent e) {
			Log.e("GestureListener", "onSingleTapConfirmed");
			return false;
		}
		//双击的第二下Touch down时触发 
		@Override
		public boolean onDoubleTap(MotionEvent e) {
			Log.e("GestureListener", "onDoubleTap");
			return false;
		}
		//双击的第二下Touch down和up都会触发，可用e.getAction()区分。
		@Override
		public boolean onDoubleTapEvent(MotionEvent e) {
			Log.e("GestureListener", "onDoubleTapEvent");
			return false;
		}
		
	}

}
