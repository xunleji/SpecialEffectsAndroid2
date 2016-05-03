package com.example.androidsdk.jar.mobiecontroller;

import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Contacts;
import android.provider.Contacts.People;
import android.util.Log;
import android.widget.Toast;

public class MobileNumberContr {
	
	private Context context;
	
	public MobileNumberContr(Context context) {
		super();
		this.context = context;
	}
	//��ѯ����:
	public void displayRecords() {
		Log.e("ToolUntil", "displayRecords");
		String columns[] = new String[] { People.NAME, People.NUMBER };
		Uri mContacts = People.CONTENT_URI;
		Cursor cur = ((Activity)context).managedQuery(mContacts,columns,  // Ҫ���ص������ֶ�
		      null,          // WHERE�Ӿ�
		      null,         // WHERE �Ӿ�Ĳ���
		      null         // Order-by�Ӿ�
		);
		if (cur.moveToFirst()) {
			Log.e("ToolUntil", "moveToFirst");
	        String name = null;
	        String phoneNo = null;
	        do {
	          // ��ȡ�ֶε�ֵ
	          name = cur.getString(cur.getColumnIndex(People.NAME));
	          phoneNo = cur.getString(cur.getColumnIndex(People.NUMBER));
	          Log.e("displayRecords", "name="+name+"phoneno="+phoneNo);
	       } while (cur.moveToNext());
	    }
	}
	//�޸ļ�¼:
	public void updateRecord(int recNo, String name) {
	    Uri uri = ContentUris.withAppendedId(People.CONTENT_URI, recNo);
	    ContentValues values = new ContentValues();
	    values.put(People.NAME, name);
	    context.getContentResolver().update(uri, values, null, null);
	}
	//��Ӽ�¼:
	public void insertRecords(String name, String phoneNo){
		ContentValues values = new ContentValues();
		values.put(People.NAME, name);
		Uri uri = context.getContentResolver().insert(People.CONTENT_URI, values);
		Log.d("insertRecords", uri.toString());
		Uri numberUri = Uri.withAppendedPath(uri, People.Phones.CONTENT_DIRECTORY);
		values.clear();
		values.put(Contacts.Phones.TYPE, People.Phones.TYPE_MOBILE);
		values.put(People.NUMBER, phoneNo);
		context.getContentResolver().insert(numberUri, values);
	}
	//ɾ����¼:
	public void deleteRecords(){
		Uri uri = People.CONTENT_URI;
		context.getContentResolver().delete(uri, null, null);
		//��Ҳ����ָ��WHERE���������ɾ���ض��ļ�¼��
		context.getContentResolver().delete(uri, "NAME=" + "��XYZ XYZ��", null);
	}

}
