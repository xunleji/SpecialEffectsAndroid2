package com.example.contentprovidertest;

import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
	
	private ContentResolver contentResolver;
	private List<String> strlist;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ListView lv = new ListView(this);
		//通过contentResolver进行查找
		strlist = new ArrayList<String>();
		contentResolver = this.getContentResolver();
		Cursor cursor = contentResolver.query(
	        RuiXin.CONTENT_URI, new String[] {RuiXin.USERNAME,
	        RuiXin.DATE,RuiXin.SEX }, null, null, null);
        while (cursor.moveToNext()) {
        	strlist.add(cursor.getString(cursor.getColumnIndex(RuiXin.USERNAME))
                    + " "+ cursor.getString(cursor.getColumnIndex(RuiXin.DATE))
                    + " "+ cursor.getString(cursor.getColumnIndex(RuiXin.SEX)));
       }
       startManagingCursor(cursor);  //查找后关闭游标
       ArrayAdapter<String> adpater = new ArrayAdapter<String>(this,
    		   android.R.layout.simple_expandable_list_item_1,strlist);
       lv.setAdapter(adpater);
       setContentView(lv);
		
	}

}
