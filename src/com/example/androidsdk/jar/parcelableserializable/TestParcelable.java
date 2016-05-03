package com.example.androidsdk.jar.parcelableserializable;

import com.example.androidsdk.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TestParcelable extends Activity implements OnClickListener{

	private Button btnPar,btnSer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.data);
		btnPar = (Button)findViewById(R.id.dataParceable);
		btnSer = (Button)findViewById(R.id.dataSerializable);
		btnPar.setOnClickListener(this);
		btnSer.setOnClickListener(this);
	}
    private Intent intent;
	@Override
	public void onClick(View v) {
		if(v==btnPar){
			intent = new Intent();   
			PersonParceable p = new PersonParceable();   
			p.setId(1);
			p.setName("louis koo");
			intent.putExtra("yes", p);   
			intent.putExtra("state", true);
			intent.setClass(this, Test.class);   
			startActivity(intent);  
		}else if(v==btnSer){
			intent = new Intent();   
			PersonSerializable p = new PersonSerializable();   
			p.setId(2);
			p.setName("carman lee");
			intent.putExtra("no", p);   
			intent.putExtra("state", false);
			intent.setClass(this, Test.class);   
			startActivity(intent); 
		}
		
	}

}
