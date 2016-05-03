package com.example.androidsdk.jar.webservice;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import com.example.androidsdk.R;
import com.example.androidsdk.jar.webservice.WebServiceUtil;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class WebDemoActivity extends Activity {
	
	private TextView tv;
	private Spinner  spinner;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webservice);
        
        tv = (TextView)findViewById(R.id.maintv3);
        spinner = (Spinner)findViewById(R.id.mainspinner);
        ArrayList<String> city = WebServiceUtil.getCityList();
        ArrayList<String> citys = new ArrayList<String>();
        citys.add("上海");
        citys.add("广州");
        citys.add("香港");
        citys.add("盐城");
        citys.add("北京");
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, citys);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				String msg = WebServiceUtil.getWeatherMsgByCity(spinner.getSelectedItem().toString());
				tv.setText(msg);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				//焦点移除时触发
				Log.e("onCreate", "onNothingSelected");
			}
		});
    }
}