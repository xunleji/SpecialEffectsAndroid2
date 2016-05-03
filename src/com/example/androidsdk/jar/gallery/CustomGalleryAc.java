package com.example.androidsdk.jar.gallery;

import com.example.androidsdk.R;
import com.example.androidsdk.jar.gallery.CustomGallery;
import com.example.androidsdk.jar.gallery.GalleryAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class CustomGalleryAc extends Activity {
	
	private CustomGallery customGallery;
	private GalleryAdapter galleryAdapter;
	private LinearLayout pointLinear;
	private int position = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.topcustomgallery);
		customGallery = (CustomGallery)findViewById(R.id.top_today_gallery);
		galleryAdapter = new GalleryAdapter(this);
		customGallery.setAdapter(galleryAdapter);
		pointLinear = (LinearLayout)findViewById(R.id.gallery_point_linear);
		for (int i = 0; i < 4; i++) {
			ImageView pointView = new ImageView(this);
			if(i==0){
				pointView.setBackgroundResource(R.drawable.point_red);
			}else
				pointView.setBackgroundResource(R.drawable.point_yellow);
			pointLinear.addView(pointView);
		}
	}
	//Gallery中圆点的切换
	public void changePointView(int cur){	
		//获取先前选中位置
    	View view = pointLinear.getChildAt(position);
    	//获取当前选中位置
    	View curView = pointLinear.getChildAt(cur);
    	if(view!=null&& curView!=null){
    		ImageView pointView = (ImageView)view;
    		ImageView curPointView = (ImageView)curView;
    		pointView.setBackgroundResource(R.drawable.point_yellow);
    		curPointView.setBackgroundResource(R.drawable.point_red);
    		position = cur;
    	}
	}

}
