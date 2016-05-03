package com.example.androidsdk.jar.customlist;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.androidsdk.R;

/**
 * listview拖拽着上下移动
 * @author xujuan
 *
 */
public class Lvone extends Activity {
	
	private static List<String> list = new ArrayList<String>();
	//组数
	private static List<String> keyGroup = new ArrayList<String>();
	//A组
    private List<String> navList = new ArrayList<String>();
    //B组
    private List<String> moreList = new ArrayList<String>();
	private DragListAdapter adapter = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lv_customlv);
		//初始化数据
		initData();
		ListView dragView = (ListView) this.findViewById(R.id.dragView);
		adapter = new DragListAdapter(this, list,keyGroup);
		dragView.setAdapter(adapter);
	}
	//初始化数据
	public void initData(){
		keyGroup.add("A组");
		for(int i = 0;i< 4; i++){
			navList.add("A组Data0"+i);
		}
		keyGroup.add("B组");
		for(int i = 0;i< 8; i++){
			moreList.add("B组Data0"+i);
		}
		list.add("A组");
		list.addAll(navList);
		list.add("B组");
		list.addAll(moreList);
	}

}
