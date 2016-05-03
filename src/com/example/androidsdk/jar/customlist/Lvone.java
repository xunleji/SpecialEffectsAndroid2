package com.example.androidsdk.jar.customlist;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.androidsdk.R;

/**
 * listview��ק�������ƶ�
 * @author xujuan
 *
 */
public class Lvone extends Activity {
	
	private static List<String> list = new ArrayList<String>();
	//����
	private static List<String> keyGroup = new ArrayList<String>();
	//A��
    private List<String> navList = new ArrayList<String>();
    //B��
    private List<String> moreList = new ArrayList<String>();
	private DragListAdapter adapter = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lv_customlv);
		//��ʼ������
		initData();
		ListView dragView = (ListView) this.findViewById(R.id.dragView);
		adapter = new DragListAdapter(this, list,keyGroup);
		dragView.setAdapter(adapter);
	}
	//��ʼ������
	public void initData(){
		keyGroup.add("A��");
		for(int i = 0;i< 4; i++){
			navList.add("A��Data0"+i);
		}
		keyGroup.add("B��");
		for(int i = 0;i< 8; i++){
			moreList.add("B��Data0"+i);
		}
		list.add("A��");
		list.addAll(navList);
		list.add("B��");
		list.addAll(moreList);
	}

}
