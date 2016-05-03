package com.example.androidsdk.jar.customlist;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.widget.ListView;

public class publicmethod {

	private int allcount = 38;
	private ArrayList<String> news ;
	private Context context;
	private LoadlvAdapter loadlvAdpater;
	
	public publicmethod(Context context) {
		super();
		this.context = context;
	}

	 //��ʼ��ListView��������
	public LoadlvAdapter initializeAdapter(){
		news = new ArrayList<String>();
		for(int i=0;i<10;i++){
			String str = "this is title"+i;
			news.add(str);
		}
		loadlvAdpater = new LoadlvAdapter(context,news);
		return loadlvAdpater;
	}
	//���ظ�������
	public void loadMoreData(LoadlvAdapter loadlvAdpater,ListView lv,View footer){
		int count = loadlvAdpater.getCount();
		if(count+10<=allcount){
			for(int i = count;i<count+10;i++){
				String str = "this is title"+i;
				news.add(str);
			}
		}else{
			for(int i = count;i<allcount;i++){
				String str = "this is title"+i;
				news.add(str);
				lv.removeFooterView(footer);
			}
		}
		loadlvAdpater.updateUI(news);
	}
}
