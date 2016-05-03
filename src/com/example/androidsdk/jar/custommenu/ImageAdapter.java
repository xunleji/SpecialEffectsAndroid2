package com.example.androidsdk.jar.custommenu;

import com.example.androidsdk.R;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class ImageAdapter extends BaseAdapter {

	private Context context;
	private String[] title = new String[]{
			"Ìí¼Ó¸èÇú", "¸èÇúÐÅÏ¢", "²éÕÒ¸è´Ê", "ËÑË÷¸è´Ê"
	};
	private int[] resArray = new int[]{
			R.drawable.icon_menu_addto, R.drawable.icon_menu_audioinfo,
			R.drawable.icon_menu_findlrc, R.drawable.icon_menu_scan
	};
	
	public ImageAdapter(Context context) {
		this.context = context;
	}
	
	@Override
	public int getCount() {
		if(resArray==null){
			return 0;
		}else{
			return resArray.length;
		}
	}

	@Override
	public Object getItem(int arg0) {
		return resArray[arg0];
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		LinearLayout linear = new LinearLayout(context);
		LinearLayout.LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, 
				LayoutParams.WRAP_CONTENT);
		linear.setOrientation(LinearLayout.VERTICAL);
		
		ImageView iv = new ImageView(context);
		iv.setImageBitmap(((BitmapDrawable)context.getResources().getDrawable(resArray[arg0])).getBitmap());
		LinearLayout.LayoutParams params2 = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params2.gravity=Gravity.CENTER;
		linear.addView(iv, params2);
		
		TextView tv = new TextView(context);
		tv.setText(title[arg0]);
		LinearLayout.LayoutParams params3 = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params3.gravity=Gravity.CENTER;
		
		linear.addView(tv, params3);
		
		return linear;
	}

}
