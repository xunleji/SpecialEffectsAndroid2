package com.example.androidsdk.jar.gallery;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Gallery;

public class CustomGallery extends Gallery {

	public CustomGallery(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		int kEvent;
		if(isScrollingLeft(e1, e2)){
			kEvent = KeyEvent.KEYCODE_DPAD_LEFT;
		}else{
			kEvent = KeyEvent.KEYCODE_DPAD_RIGHT;
		}
		//����������
		onKeyDown(kEvent,null); 
		return false;
	}
	//�ж����������󻬶��������һ���
	private boolean isScrollingLeft(MotionEvent e1, MotionEvent e2){
        return e2.getX() > e1.getX();
    }
}
