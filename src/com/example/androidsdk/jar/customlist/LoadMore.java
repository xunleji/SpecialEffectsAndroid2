package com.example.androidsdk.jar.customlist;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.example.androidsdk.R;
import com.example.androidsdk.jar.customlist.LoadlvAdapter;
import com.example.androidsdk.jar.customlist.publicmethod;

/**
 * listview��ҳ����
 * @author xujuan
 *
 */
public class LoadMore extends Activity {
	
	private View loadMoreView;
	private ListView loadMorelv;
	private Button loadMorebtn;
	private LoadlvAdapter loadLvAdpater;
	private Handler loadlvHandler = new Handler();
	private publicmethod method;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview);
		//listview�ײ�
		loadMoreView = LayoutInflater.from(this).inflate(R.layout.listview_item_btn, null);
		loadMorebtn = (Button)loadMoreView.findViewById(R.id.loadMoreButton);
		loadMorebtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				loadMorebtn.setText("���ڼ�����....");
				loadlvHandler.postDelayed(new Runnable() {
					
					@Override
					public void run() {
						//��������
						loadMorebtn.setText("�鿴����...");
						method.loadMoreData(loadLvAdpater,loadMorelv,loadMoreView);
					}
				}, 2000);
				
			}
		});
		loadMorelv = (ListView)findViewById(R.id.lvNews);
		loadMorelv.addFooterView(loadMoreView);
		loadMorelv.setDividerHeight(0);
		method = new publicmethod(this);
		loadLvAdpater = method.initializeAdapter();
		loadMorelv.setAdapter(loadLvAdpater);
	}

}
