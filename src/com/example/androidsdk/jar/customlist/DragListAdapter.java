package com.example.androidsdk.jar.customlist;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.androidsdk.R;

//渲染不同的view
public class DragListAdapter extends ArrayAdapter<String>{
	
	private static List<String> list ;
	private static List<String> keyGroup ;
	
	public DragListAdapter(Context context,List<String> lists,List<String> listgroup) {
		super(context, 0, lists);
		this.list = lists;
		this.keyGroup = listgroup;
	}
	
	public List<String> getList(){
		return list;
	}

	//检查数据项是否为标题项然后标记是否可以更改
	@Override
	public boolean isEnabled(int position) {
		 if(keyGroup.contains(getItem(position))){
			 return false;
		 }
		return super.isEnabled(position);
	}

	//利用模板布局不同的listview
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if(keyGroup.contains(getItem(position))){
			//标题组
			view = LayoutInflater.from(getContext()).inflate(R.layout.lv_textview, null);
		}else{
			//图片组
			view = LayoutInflater.from(getContext()).inflate(R.layout.lv_textandimage, null);
		}
		TextView textView = (TextView) view.findViewById(R.id.headtext);
		textView.setText(getItem(position));
		return view;
	}
}
