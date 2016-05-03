package com.example.androidsdk.jar.contentprovider;

import java.util.ArrayList;
import java.util.List;

import com.example.androidsdk.R;
import com.example.androidsdk.jar.contentprovider.DBlite;
import com.example.androidsdk.jar.contentprovider.RuiXin;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class ContentProviderTest extends Activity implements OnClickListener{
	
	private DBlite dBlite1 ;
    private ContentResolver contentResolver;
    private Button addbtn,delbtn,querybtn,updatebtn;
    private ListView lv;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		dBlite1 = new DBlite(this);
		setContentView(R.layout.contentprovide);
		addbtn = (Button)findViewById(R.id.btnadd);
		delbtn = (Button)findViewById(R.id.btndelete);
		querybtn = (Button)findViewById(R.id.btnquery);
		updatebtn = (Button)findViewById(R.id.btnupdate);
		addbtn.setOnClickListener(this);
		delbtn.setOnClickListener(this);
		querybtn.setOnClickListener(this);
		updatebtn.setOnClickListener(this);
		lv = (ListView)findViewById(R.id.lv);
	}

	private List<String> strlist;
	@Override
	public void onClick(View v) {
		if(v==addbtn){
			dBlite1.add("carman lee","time="+System.currentTimeMillis(),"女");
		}else if(v==delbtn){
			dBlite1.del();
		}else if(v==querybtn){
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
	       ArrayAdapter<String> adpater = new ArrayAdapter<String>(ContentProviderTest.this,
	    		   android.R.layout.simple_expandable_list_item_1,strlist);
	       lv.setAdapter(adpater);
		}else if(v==updatebtn){
			dBlite1.update();
		}
		
	}

}
