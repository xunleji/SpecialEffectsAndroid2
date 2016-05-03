package com.example.androidsdk.jar.gallery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import com.example.androidsdk.R;

public class AlignLeftAdapter extends BaseAdapter {

	private Context context;
	private int mScreenWidth;
	
	public AlignLeftAdapter(Context context,int width) {
		super();
		this.context = context;
		this.mScreenWidth = width;
	}

	@Override
	public int getCount() {
		return 100;
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		if (null == convertView) {
			convertView = LayoutInflater.from(context)
					.inflate(R.layout.detail_gallery_item, null);
			Gallery.LayoutParams lp = new Gallery.LayoutParams(
					mScreenWidth / 4, Gallery.LayoutParams.FILL_PARENT);
			convertView.setLayoutParams(lp);
		}
		ImageView img = (ImageView)convertView.findViewById(R.id.img);
		img.setImageResource(R.drawable.ic_launcher);
//		img.setOnTouchListener(new OnTouchListener() {
//			
//			@Override
//			public boolean onTouch(View v, MotionEvent event) {
//				Log.i(TAG, "onTouch -----------------");
//				return false;
//			}
//		});
//		img.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				Log.i(TAG, "onClick -----------------");
//			}
//		});
		
		return convertView;
	}
}
