package com.example.androidsdk.jar.mobiecontroller;

import com.example.androidsdk.R;
import com.example.androidsdk.jar.mobiecontroller.MobileNumberContr;

import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts.People;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class NumberMobieTest extends Activity implements OnClickListener{

	private MobileNumberContr numbermobie;
	private Button btn1,btn2,btn3,btn4;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.datatest);
		
		numbermobie = new MobileNumberContr(this);
		
		btn1 = (Button)findViewById(R.id.showtbtn);
		btn2 = (Button)findViewById(R.id.inserttbtn);
		btn3 = (Button)findViewById(R.id.updatetbtn);
		btn4 = (Button)findViewById(R.id.deltbtn);
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
		btn4.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		if(v==btn1){
			numbermobie.displayRecords();
		}else if(v==btn2){
			numbermobie.insertRecords("π≈ÃÏ¿÷", "15251508896");
		}else if(v==btn3){
			numbermobie.updateRecord(1, "π≈ÃÏ¿÷");
		}else if(v==btn4){
			numbermobie.deleteRecords();
		}
		
	}
	
}
