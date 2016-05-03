package com.example.androidsdk.jar.menuslowopen;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.androidsdk.R;

/**
 * 菜单动画效果
 * @author xujuan
 *
 */
public class Mainanimation extends Activity implements OnClickListener{
	
	private Animation showAction, hideAction; 
	private LinearLayout menu; 
	private Button btn1,btn2,btn3,btn4; 
	private boolean menuShowed;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.animationmain);
		
		menu = (LinearLayout)findViewById(R.id.ll);
		btn1 = (Button)findViewById(R.id.btn1);
		btn2 = (Button)findViewById(R.id.btn2);
		btn3 = (Button)findViewById(R.id.btn3);
		btn4 = (Button)findViewById(R.id.btn4);
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
		btn4.setOnClickListener(this);
		
		showAction = new ScaleAnimation(
				1.0f,1.0f,0.0f,1.0f,Animation.RELATIVE_TO_SELF,0.0f, 
				Animation.RELATIVE_TO_SELF,0.0f); 
		showAction.setDuration(500); 
		
		hideAction = new ScaleAnimation(1.0f,1.0f,1.0f,0.0f,
				Animation.RELATIVE_TO_SELF, 0.0f,
				Animation.RELATIVE_TO_SELF, 0.0f); 
		hideAction.setDuration(500); 
		menuShowed = false; 
		menu.setVisibility(View.GONE); 
		
		Button btn = (Button)findViewById(R.id.mainbtn);
		btn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.mainbtn:
			if (menuShowed) { 
				menuShowed = false; 
				menu.startAnimation(hideAction);
				menu.setVisibility(View.GONE); 
			}else { 
				menuShowed = true; 
				menu.startAnimation(showAction); 
				menu.setVisibility(View.VISIBLE); 
			} 
			break;
		case R.id.btn1:
			Toast.makeText(getApplicationContext(), "btn1", 1000).show();	
			break;
		case R.id.btn2:
			Toast.makeText(getApplicationContext(), "btn2", 1000).show();	
			break;
		case R.id.btn3:
			Toast.makeText(getApplicationContext(), "btn3", 1000).show();	
			break;
		case R.id.btn4:
			Toast.makeText(getApplicationContext(), "btn4", 1000).show();	
			break;
		default:
			break;
		}
		
	}

}
