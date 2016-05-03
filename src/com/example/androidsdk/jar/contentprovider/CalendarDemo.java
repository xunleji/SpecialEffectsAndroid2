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

/**��������ʱ������
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
	//Ϊ�˼��ݲ�ͬ�汾������,2.2�Ժ�url�����ı� 
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
			//��ȡҪ�����gmail�˻���id 
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
			//����hoohbood@gmail.com����˻� 
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
			//��ǰ10���������� 
			values.put( "minutes", 10 ); 
			getContentResolver().insert(Uri.parse(calanderRemiderURL), values); 
			Toast.makeText(CalendarDemo.this, "�����¼��ɹ�!!!", Toast.LENGTH_LONG).show(); 
		} 

	}

}
