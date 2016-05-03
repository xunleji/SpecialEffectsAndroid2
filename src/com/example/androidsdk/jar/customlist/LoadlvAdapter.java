package com.example.androidsdk.jar.customlist;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.androidsdk.R;

public class LoadlvAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<String> strs;
	
	public LoadlvAdapter(Context context,ArrayList<String> str) {
		super();
		this.context = context;
		this.strs = str;
	}

	@Override
	public int getCount() {
		return strs.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder vh;
		if(convertView==null){
			vh = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.listview_item, null);
			vh.tv = (TextView)convertView.findViewById(R.id.newstitle);
			convertView.setTag(vh);
		}else{
			vh = (ViewHolder)convertView.getTag();
		}
		vh.tv.setText(strs.get(position));
		return convertView;
	}
	
	private class ViewHolder{
		public TextView tv;
	}
	
	public void updateUI(ArrayList<String> str){
		this.strs = str;
		notifyDataSetChanged();
	}

}
