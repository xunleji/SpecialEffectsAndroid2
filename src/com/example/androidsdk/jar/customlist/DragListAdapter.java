package com.example.androidsdk.jar.customlist;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.androidsdk.R;

//��Ⱦ��ͬ��view
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

	//����������Ƿ�Ϊ������Ȼ�����Ƿ���Ը���
	@Override
	public boolean isEnabled(int position) {
		 if(keyGroup.contains(getItem(position))){
			 return false;
		 }
		return super.isEnabled(position);
	}

	//����ģ�岼�ֲ�ͬ��listview
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if(keyGroup.contains(getItem(position))){
			//������
			view = LayoutInflater.from(getContext()).inflate(R.layout.lv_textview, null);
		}else{
			//ͼƬ��
			view = LayoutInflater.from(getContext()).inflate(R.layout.lv_textandimage, null);
		}
		TextView textView = (TextView) view.findViewById(R.id.headtext);
		textView.setText(getItem(position));
		return view;
	}
}
