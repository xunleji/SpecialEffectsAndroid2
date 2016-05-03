package com.example.androidsdk.jar.useryindao;

import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.ImageView;

import com.example.androidsdk.R;

/**
 * Ð¡Ô²µãµÄÇÐ»»
 * @author xujuan
 *
 */
public class PointChange implements OnPageChangeListener{

	private ImageView[] imageViews;
	
	public PointChange(ImageView[] imageViews) {
		super();
		this.imageViews = imageViews;
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		for(int i = 0;i<imageViews.length;i++){
			imageViews[arg0].setBackgroundResource(R.drawable.point_red);
			if(arg0!=i){
				imageViews[i].setBackgroundResource(R.drawable.point_yellow);
			}
		}	
	}

}
