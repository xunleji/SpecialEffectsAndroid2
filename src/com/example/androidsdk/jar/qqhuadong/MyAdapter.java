package com.example.androidsdk.jar.qqhuadong;

import com.example.androidsdk.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter{
	
	//上下文对象 
    private Context context; 
    //图片数组 
    private Integer[] imgs = { 
           R.drawable.zzz_skin_white_personal_qzone,R.drawable.zzz_skin_white_personal_weibo,
           R.drawable.zzz_skin_white_personal_mail,R.drawable.zzz_skin_white_near_people_setting_normal,
           R.drawable.zzz_skin_white_personal_account,R.drawable.zzz_skin_white_personal_exit
    }; 
    private String str[]={"QQ空间","腾讯微博","QQ邮箱","软件设置","账号管理","退出程序"};
    
    public MyAdapter(Context context){ 
        this.context = context; 
    } 
    public int getCount() { 
        return imgs.length; 
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
        	 convertView = LayoutInflater.from(context).inflate(R.layout.gv_item, null);
        	 vh.iv = (ImageView)convertView.findViewById(R.id.gv_item_iv);
        	 vh.tv = (TextView)convertView.findViewById(R.id.gv_item_tv);
        	 vh.iv.setAdjustViewBounds(false);//设置边界对齐 
        	 vh.iv.setScaleType(ImageView.ScaleType.CENTER_CROP);//设置刻度的类型 
        	 vh.iv.setPadding(8, 8, 8, 8);//设置间距 
             convertView.setTag(vh);
 		}else{
 			vh = (ViewHolder)convertView.getTag();
 		}
         vh.iv.setImageResource(imgs[position]);//为ImageView设置图片资源 
         vh.tv.setText(str[position]);
         return convertView; 
    } 
    
    class ViewHolder{
    	public TextView tv;
    	public ImageView iv;
    }
}
