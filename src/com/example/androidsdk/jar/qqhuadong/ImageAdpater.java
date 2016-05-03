package com.example.androidsdk.jar.qqhuadong;

import com.example.androidsdk.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdpater extends BaseAdapter {

	//上下文对象 
    private Context context; 
    
    public ImageAdpater(Context context){ 
        this.context = context; 
    } 
    public int getCount() { 
        return 30; 
    } 

    public Object getItem(int item) { 
        return item; 
    } 

    public long getItemId(int id) { 
        return id; 
    } 
     
    //创建View方法 
    public View getView(int position, View convertView, ViewGroup parent) { 
         ViewHolder vh;
         if (convertView == null) { 
        	 vh = new ViewHolder();
        	 convertView = LayoutInflater.from(context).inflate(R.layout.lv_item, null);
        	 vh.iv = (ImageView)convertView.findViewById(R.id.centeriv);
        	 vh.tv = (TextView)convertView.findViewById(R.id.centertv);
             convertView.setTag(vh);
 		}else{
 			vh = (ViewHolder)convertView.getTag();
 		}
         return convertView; 
    } 
    
    class ViewHolder{
    	public TextView tv;
    	public ImageView iv;
    }
}
