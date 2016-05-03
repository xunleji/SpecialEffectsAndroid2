package com.example.androidsdk.jar.shareperfence;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * shareperfence数据保存形式
 * @author xujuan
 *
 */
public class DataDB {

	private Context context;// 应用环境上下文 Activity 是其子类
    private String record = "datas";
    
	public DataDB(Context context) {
		super();
		this.context = context;
	}
	//保存数据
	public void sDown(String str) {
    	
		Editor data = context.getSharedPreferences(record,
				Context.MODE_WORLD_READABLE).edit();
		data.remove("data");
		data.putString("data", str);
		data.commit();
    }
	//获取数据
	public String loadDown() {
		SharedPreferences data = context.getSharedPreferences(record,
				Context.MODE_WORLD_READABLE);
		return data.getString("data", null);
    }
	//删除所有数据
	public boolean deleteAll() {
		try {
			Editor data = context.getSharedPreferences(record,
					Context.MODE_WORLD_READABLE).edit();
			data.clear();
			data.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
