package com.example.androidsdk.jar.qqhuadong;

import com.example.androidsdk.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

public class QQmini2Activity extends Activity {
	private HomeCenterLayout centerLayout;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.qqtest);
		
		initLeft();
		centerLayout = (HomeCenterLayout) findViewById(R.id.homeCenter);
		initRight();
		
		centerLayout.setBrotherLayout(leftLayout, rightLayout);
		
		ListView commonlv = (ListView)findViewById(R.id.commonlv);
		ImageAdpater imageAdapter = new ImageAdpater(this);
		commonlv.setAdapter(imageAdapter);
		commonlv.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(QQmini2Activity.this, "lv2222"+arg2, 2000).show();
			}
		});
		
		ImageView leftBtn = (ImageView) findViewById(R.id.ivleft);
		ImageView rightBtn = (ImageView) findViewById(R.id.ivright);
		leftBtn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (centerLayout.getPage() == HomeCenterLayout.MIDDLE)
					centerLayout.setPage(HomeCenterLayout.LEFT);
				else
					centerLayout.setPage(HomeCenterLayout.MIDDLE);
			}
		});

		rightBtn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (centerLayout.getPage() == HomeCenterLayout.MIDDLE)
					centerLayout.setPage(HomeCenterLayout.RIGHT);
				else
					centerLayout.setPage(HomeCenterLayout.MIDDLE);
			}
		});
	}
	private LinearLayout leftLayout ;
	
	private void initLeft(){
		leftLayout = (LinearLayout) findViewById(R.id.homeLeft);
		leftLayout.setVisibility(View.GONE);
		ListView lv = (ListView)findViewById(R.id.homeLeftlv);
		String str[]={"my0","my1","my2","my3","my4","my5","my6","my7","my8","my9",
				"my10","my11","my12","my13","my14","my15","my16","my17","my18","my19",
				"my20","my21","my22","my23","my24","my25","my26","my27","my28","my29"};
		ArrayAdapter<String> arrayAdpater = new ArrayAdapter<String>(this, 
				android.R.layout.simple_expandable_list_item_1, str);
		lv.setAdapter(arrayAdpater);
		lv.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(QQmini2Activity.this, "my"+arg2, 2000).show();
			}
		});
	}
	
	private ImageView ivcenterleft,ivcenterright;
	private GridView rightgv;
	private LinearLayout rightLayout ;
	
	private void initRight(){
		rightLayout = (LinearLayout) findViewById(R.id.homeRight);
		rightLayout.setVisibility(View.GONE);
		ivcenterleft = (ImageView)findViewById(R.id.ivcenterleft);
		ivcenterleft.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(QQmini2Activity.this, "更换主题", 2000).show();
			}
		});
		ivcenterright = (ImageView)findViewById(R.id.ivcenterright);
		ivcenterright.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(QQmini2Activity.this, "附近的人", 2000).show();
			}
		});
		rightgv = (GridView)findViewById(R.id.rightgv);
		MyAdapter myAdapter = new MyAdapter(this);
		final String str[]={"QQ空间","腾讯微博","QQ邮箱","软件设置","账号管理","退出程序"};
		rightgv.setAdapter(myAdapter);
		rightgv.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(QQmini2Activity.this, str[arg2], 2000).show();
			}
		});
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		if (centerLayout.getPage() != HomeCenterLayout.MIDDLE)
			centerLayout.setPage(HomeCenterLayout.MIDDLE);
		else
			super.onBackPressed();
	}
}