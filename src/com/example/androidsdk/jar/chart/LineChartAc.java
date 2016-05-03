package com.example.androidsdk.jar.chart;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.LinearLayout;

import com.example.androidsdk.R;
import com.example.androidsdk.jar.chart.LineChart;

/**
 * ÕÛÏßÍ¼/Öù×´Í¼
 * @author xujuan
 *
 */
public class LineChartAc extends Activity {

	private LinearLayout ll;
	private LineChart lineChart;
	private List<Integer> lineInt;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.linechart);
		DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;  // ÆÁÄ»¿í¶È£¨ÏñËØ£©
        int height = metric.heightPixels;  // ÆÁÄ»¸ß¶È£¨ÏñËØ£©
        
		ll = (LinearLayout)findViewById(R.id.linechart);
		lineChart = new LineChart(this,width,height);
        lineInt = new ArrayList<Integer>();
        lineInt.add(0);
        lineInt.add(2);
        lineInt.add(5);
        lineInt.add(7);
        lineInt.add(0);
        lineInt.add(2);
        lineInt.add(0);
        lineInt.add(8);
        lineInt.add(0);
        lineInt.add(5);
        lineInt.add(0);
        lineInt.add(0);
        lineChart.SetInfo(
        		new String[]{"1","2","3","4","5","6","7","8","9","10","11","12"},
        		new String[]{"0%","25%","50%","75%","100%"}, 
        		lineInt);
		ll.addView(lineChart);
	}

}
