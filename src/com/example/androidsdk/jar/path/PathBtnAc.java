package com.example.androidsdk.jar.path;

import com.example.androidsdk.R;
import android.R.anim;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class PathBtnAc extends Activity {
	
	private boolean areButtonsShowing;
	private RelativeLayout composerButtonsWrapper;
	private ImageView composerButtonsShowHideButtonIcon;
	private RelativeLayout composerButtonsShowHideButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainpath);
		
		MyAnimations.initOffset(PathBtnAc.this);
		composerButtonsWrapper = (RelativeLayout)findViewById(R.id.composer_buttons_wrapper);
		composerButtonsShowHideButton = (RelativeLayout)findViewById(R.id.composer_buttons_show_hide_button);
		composerButtonsShowHideButtonIcon = (ImageView)findViewById(R.id.composer_buttons_show_hide_button_icon);
		final int length = composerButtonsWrapper.getChildCount();
		for (int i = 0; i < length; i++) {
			final int a = i;
			composerButtonsWrapper.getChildAt(i).setOnClickListener(
					new View.OnClickListener() {
						@Override
						public void onClick(View arg0) {
							for(int j = 0;j<length;j++){
								if(j==a){
									composerButtonsWrapper.getChildAt(j).startAnimation(setAnimScale(1.5f, 1.5f));
								}else{
									composerButtonsWrapper.getChildAt(j).startAnimation(setAnimScale(0.0f, 0.0f));
									composerButtonsShowHideButton.startAnimation(setAnimScale(0.0f, 0.0f));
								}
							}
						}
					});
		}
		
		composerButtonsShowHideButton.startAnimation(MyAnimations
				.getRotateAnimation(0, 360, 200));
		composerButtonsShowHideButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!areButtonsShowing) {
					MyAnimations.startAnimationsIn(composerButtonsWrapper, 300);
					composerButtonsShowHideButtonIcon
							.startAnimation(MyAnimations.getRotateAnimation(
									0,-270, 300));
				} else {
					MyAnimations.startAnimationsOut(composerButtonsWrapper, 300);
					composerButtonsShowHideButtonIcon
							.startAnimation(MyAnimations.getRotateAnimation(
									-270, 0, 300));
				}
				areButtonsShowing = !areButtonsShowing;
			}
		});
	}
	private Animation animationScale;
	
	protected Animation setAnimScale(float toX, float toY){
		// TODO Auto-generated method stub
		animationScale = new ScaleAnimation(1f, toX, 1f, toY, 
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.45f);
		animationScale.setInterpolator(PathBtnAc.this, anim.accelerate_decelerate_interpolator);
		animationScale.setDuration(500);
		animationScale.setFillAfter(false);
		return animationScale;
		
	}

}
