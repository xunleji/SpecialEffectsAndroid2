package com.example.androidsdk.jar.chart;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.androidsdk.R;
import com.example.androidsdk.jar.chart.PieChart;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.LinearLayout;

/**
 * ±ý×´Í¼
 * @author xujuan
 *
 */
public class PieChartAc extends Activity{
	
	private PieChart pieChart ;
	private LinearLayout ll;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.linechart);
		DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;  // ÆÁÄ»¿í¶È£¨ÏñËØ£©
        int height = metric.heightPixels;  // ÆÁÄ»¸ß¶È£¨ÏñËØ£©
        ll = (LinearLayout)findViewById(R.id.linechart);
        JSONArray js = new JSONArray();
        try{
        	JSONObject jso1 = new JSONObject();
        	jso1.put("money", 10);
        	jso1.put("name", "louis1");
        	js.put(jso1);
        	JSONObject jso2 = new JSONObject();
        	jso2.put("money", 11);
        	jso2.put("name", "louis2");
        	js.put(jso2);
        	JSONObject jso3 = new JSONObject();
        	jso3.put("money", 13);
        	jso3.put("name", "louis3");
        	js.put(jso3);
        }catch (Exception e) {
			e.printStackTrace();
		}
    	pieChart = new PieChart(this, js, 34, width, height);
    	ll.addView(pieChart);
	}

}
