package com.example.androidsdk.jar.contentprovider;

import java.util.Calendar;

import com.example.androidsdk.R;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**设置日历时间提醒
 * @author xujuan
 *
 */
public class CalendarDemo extends Activity implements OnClickListener {
	
	private Button mReadUserButton; 
	private Button mReadEventButton; 
	private Button mWriteEventButton; 

	private static String calanderURL = ""; 
	private static String calanderEventURL = ""; 
	private static String calanderRemiderURL = ""; 
	//为了兼容不同版本的日历,2.2以后url发生改变 
	static{ 
		if(Integer.parseInt(Build.VERSION.SDK) >= 8){ 
			calanderURL = "content://com.android.calendar/calendars"; 
			calanderEventURL = "content://com.android.calendar/events"; 
			calanderRemiderURL = "content://com.android.calendar/reminders"; 
	
		}else{ 
			calanderURL = "content://calendar/calendars"; 
			calanderEventURL = "content://calendar/events"; 
			calanderRemiderURL = "content://calendar/reminders"; 
		} 
	} 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calendar);
		setupViews(); 
	}
	private void setupViews(){ 
		mReadUserButton = (Button)findViewById(R.id.readUserButton); 
		mReadEventButton = (Button)findViewById(R.id.readEventButton); 
		mWriteEventButton = (Button)findViewById(R.id.writeEventButton); 
		mReadUserButton.setOnClickListener(this); 
		mReadEventButton.setOnClickListener(this); 
		mWriteEventButton.setOnClickListener(this); 
	} 

	@Override
	public void onClick(View v) {
		if(v == mReadUserButton){ 
			Cursor userCursor = getContentResolver().query(Uri.parse(calanderURL), null, 
						null, null, null); 
			if(userCursor.getCount() > 0){ 
				userCursor.moveToFirst(); 
				String userName = userCursor.getString(userCursor.getColumnIndex("name")); 
				Toast.makeText(CalendarDemo.this, userName, Toast.LENGTH_LONG).show(); 
			} 
		}else if(v == mReadEventButton){ 
			Cursor eventCursor = getContentResolver().query(Uri.parse(calanderEventURL), null, 
						null, null, null); 
			if(eventCursor.getCount() > 0){ 
				eventCursor.moveToLast(); 
				String eventTitle = eventCursor.getString(eventCursor.getColumnIndex("title")); 
				Toast.makeText(CalendarDemo.this, eventTitle, Toast.LENGTH_LONG).show(); 
			} 
		}else if(v == mWriteEventButton){ 
			//获取要出入的gmail账户的id 
			String calId = ""; 
			Cursor userCursor = getContentResolver().query(Uri.parse(calanderURL), null, 
						null, null, null); 
			if(userCursor.getCount() > 0){ 
				userCursor.moveToFirst(); 
				calId = userCursor.getString(userCursor.getColumnIndex("_id")); 
			} 
			ContentValues event = new ContentValues(); 
			event.put("title", "louis koo"); 
			event.put("description", "carman lee"); 
			//插入hoohbood@gmail.com这个账户 
			event.put("calendar_id",calId); 

			Calendar mCalendar = Calendar.getInstance(); 
			mCalendar.set(Calendar.HOUR_OF_DAY,10); 
			long start = mCalendar.getTime().getTime(); 
			mCalendar.set(Calendar.HOUR_OF_DAY,11); 
			long end = mCalendar.getTime().getTime(); 

			event.put("dtstart", start); 
			event.put("dtend", end); 
			event.put("hasAlarm",1); 

			Uri newEvent = getContentResolver().insert(Uri.parse(calanderEventURL), event); 
			long id = Long.parseLong( newEvent.getLastPathSegment() ); 
			ContentValues values = new ContentValues(); 
			values.put( "event_id", id ); 
			//提前10分钟有提醒 
			values.put( "minutes", 10 ); 
			getContentResolver().insert(Uri.parse(calanderRemiderURL), values); 
			Toast.makeText(CalendarDemo.this, "插入事件成功!!!", Toast.LENGTH_LONG).show(); 
		} 

	}

}
