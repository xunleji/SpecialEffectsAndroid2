package com.example.androidsdk.jar.gallery;


import com.example.androidsdk.R;
import com.example.androidsdk.jar.gallery.AlignLeftAdapter;
import com.example.androidsdk.jar.gallery.AlignLeftGallery;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

public class AlignLeftGalleryActivity extends Activity {
	
	private AlignLeftGallery mGallery;
	private AlignLeftAdapter mAdapter;
	private int mScreenWidth;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alignleftgallery);
        mScreenWidth = getWindowManager().getDefaultDisplay().getWidth();
        mAdapter = new AlignLeftAdapter(this,mScreenWidth);
        mGallery = (AlignLeftGallery)findViewById(R.id.gallery);
        mGallery.setAdapter(mAdapter);
        
        mGallery.setOnItemClickListener(new AlignLeftGallery.IOnItemClickListener() {
			
			@Override
			public void onItemClick(int position) {
				Toast.makeText(AlignLeftGalleryActivity.this, position + " click!", Toast.LENGTH_SHORT).show();
			}
		});
    }
    
}