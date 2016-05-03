package com.example.androidsdk.jar.shareperfence;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * shareperfence���ݱ�����ʽ
 * @author xujuan
 *
 */
public class DataDB {

	private Context context;// Ӧ�û��������� Activity ��������
    private String record = "datas";
    
	public DataDB(Context context) {
		super();
		this.context = context;
	}
	//��������
	public void sDown(String str) {
    	
		Editor data = context.getSharedPreferences(record,
				Context.MODE_WORLD_READABLE).edit();
		data.remove("data");
		data.putString("data", str);
		data.commit();
    }
	//��ȡ����
	public String loadDown() {
		SharedPreferences data = context.getSharedPreferences(record,
				Context.MODE_WORLD_READABLE);
		return data.getString("data", null);
    }
	//ɾ����������
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
