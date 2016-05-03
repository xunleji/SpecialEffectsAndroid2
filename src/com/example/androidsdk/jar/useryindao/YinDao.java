package com.example.androidsdk.jar.useryindao;

import java.util.ArrayList;

import com.example.androidsdk.R;
import com.example.androidsdk.jar.useryindao.ListAdapter;
import com.example.androidsdk.jar.useryindao.PointChange;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class YinDao extends Activity {

	private ArrayList<View> list;
	private ImageView[] imageViews;
	private ViewPager viewPager;
	private LinearLayout grouppoint;
	private ImageView imageView;
	private LayoutInflater inflater;
	private View guidepage;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		inflater = LayoutInflater.from(this);
		guidepage = inflater.inflate(R.layout.yindaomain, null);
		list = new ArrayList<View>();
    	list.add(inflater.inflate(R.layout.yindao1, null));
    	list.add(inflater.inflate(R.layout.yindao2, null));
    	list.add(inflater.inflate(R.layout.yindao3, null));
    	list.add(inflater.inflate(R.layout.yindao4, null));
    	imageViews = new ImageView[list.size()];
    	viewPager = (ViewPager)guidepage.findViewById(R.id.viewPager);
    	grouppoint = (LinearLayout)guidepage.findViewById(R.id.viewGroup);
		for(int i = 0;i<list.size();i++){
			imageView = new ImageView(this);
			imageView.setLayoutParams(new LayoutParams(15, 15));
			imageView.setPadding(10, 0,10, 0);
			imageViews[i] = imageView;
			if(i == 0 ){
				imageViews[i].setBackgroundResource(R.drawable.point_red);
			}else{
				imageViews[i].setBackgroundResource(R.drawable.point_yellow);
			}
			grouppoint.addView(imageView);
		}	
		setContentView(guidepage);
		viewPager.setAdapter(new ListAdapter(list));
		viewPager.setOnPageChangeListener(new PointChange(imageViews));
	}

}
